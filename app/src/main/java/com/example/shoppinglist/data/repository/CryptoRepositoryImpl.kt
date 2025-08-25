package com.example.shoppinglist.data.repository

import android.app.Application
import com.example.shoppinglist.data.database.AppDatabase
import com.example.shoppinglist.data.mapper.toCryptoItem
import com.example.shoppinglist.data.mapper.toCryptoItemDbModel
import com.example.shoppinglist.data.network.ApiFactory
import com.example.shoppinglist.domain.model.CryptoItem
import com.example.shoppinglist.domain.repository.CryptoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CryptoRepositoryImpl(
    private val application: Application
): CryptoRepository {

    private val apiService = ApiFactory.apiService
    private val cryptoDao = AppDatabase.getInstance(application).cryptoInfoDao()

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
        while (true){
            try{
                val cryptoListNet = apiService.getCrypto()
                val cryptoListDb = cryptoListNet.map { it.toCryptoItemDbModel() }
                cryptoDao.insertCryptoList(cryptoListDb)
            } catch (e: Exception){}
            delay(20000)
        }
    }
}