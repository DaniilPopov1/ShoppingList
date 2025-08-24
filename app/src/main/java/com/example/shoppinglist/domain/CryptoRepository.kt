package com.example.shoppinglist.domain

import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    fun getCryptoList(): Flow<List<CryptoItem>>

    fun getCryptoItem(id: String): Flow<CryptoItem>

    suspend fun loadData()
}