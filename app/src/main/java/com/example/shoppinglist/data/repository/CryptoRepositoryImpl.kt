package com.example.shoppinglist.data.repository

import android.content.Context
import com.example.shoppinglist.data.database.AppDatabase
import com.example.shoppinglist.data.database.CryptoInfoDao
import com.example.shoppinglist.data.mapper.toCryptoItem
import com.example.shoppinglist.data.mapper.toCryptoItemDbModel
import com.example.shoppinglist.data.network.ApiFactory
import com.example.shoppinglist.data.network.ApiService
import com.example.shoppinglist.domain.model.CryptoItem
import com.example.shoppinglist.domain.model.CryptoResult
import com.example.shoppinglist.domain.repository.CryptoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val cryptoDao: CryptoInfoDao,
    private val apiService: ApiService
): CryptoRepository {

    private var timer: Timer? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _cryptoFlow = MutableSharedFlow<CryptoResult>(  )
    //override val getCryptoList: Flow<CryptoResult> = _cryptoFlow.asSharedFlow()
    override fun getCryptoList(): Flow<CryptoResult> = _cryptoFlow.asSharedFlow()


    override fun getCryptoItem(id: String): Flow<CryptoItem> {
        return cryptoDao.getCryptoItem(id).map {
            it.toCryptoItem()
        }
    }

    override suspend fun loadData() {
        if (timer != null) return

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                scope.launch {
                    refreshData()
                }
            }
        }, 0, 20000)
    }

    fun stopLoadData() {
        timer?.cancel()
        timer = null
    }

    override suspend fun refreshData() {
        try {
            val cryptoListNet = apiService.getCrypto()
            val cryptoListDb = cryptoListNet.map { it.toCryptoItemDbModel() }
            cryptoDao.insertCryptoList(cryptoListDb)

            val cryptoList = cryptoListDb.map { it.toCryptoItem() }
            _cryptoFlow.emit(CryptoResult.Success(cryptoList))
        } catch (e: Exception) {
            _cryptoFlow.emit(CryptoResult.Error("Ошибка сети: ${e.localizedMessage ?: "Неизвестная ошибка"}"))
        }
    }
}