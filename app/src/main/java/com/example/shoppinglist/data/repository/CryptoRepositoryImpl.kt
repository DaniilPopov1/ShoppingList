package com.example.shoppinglist.data.repository

import android.content.Context
import com.example.shoppinglist.data.database.AppDatabase
import com.example.shoppinglist.data.mapper.toCryptoItem
import com.example.shoppinglist.data.mapper.toCryptoItemDbModel
import com.example.shoppinglist.data.network.ApiFactory
import com.example.shoppinglist.domain.model.CryptoItem
import com.example.shoppinglist.domain.repository.CryptoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class CryptoRepositoryImpl(
    private val context: Context
): CryptoRepository {

    private val apiService = ApiFactory.apiService
    private val cryptoDao = AppDatabase.getInstance(context).cryptoInfoDao()

    private var timer: Timer? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun getCryptoList(): Flow<List<CryptoItem>> {
        return cryptoDao.getCryptoList().map { list ->
            list.map {
                it.toCryptoItem()
            }
        }
    }

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
                    try {
                        val cryptoListNet = apiService.getCrypto()
                        val cryptoListDb = cryptoListNet.map { it.toCryptoItemDbModel() }
                        cryptoDao.insertCryptoList(cryptoListDb)
                    } catch (e: Exception) { }
                }
            }
        }, 0, 20000)
    }

    fun stopLoadData() {
        timer?.cancel()
        timer = null
    }
}