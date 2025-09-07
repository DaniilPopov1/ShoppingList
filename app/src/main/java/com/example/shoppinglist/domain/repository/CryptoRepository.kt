package com.example.shoppinglist.domain.repository

import com.example.shoppinglist.domain.model.CryptoItem
import com.example.shoppinglist.domain.model.CryptoResult
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    fun getCryptoList(): Flow<CryptoResult>

    fun getCryptoItem(id: String): Flow<CryptoItem>

    suspend fun loadData()
}