package com.example.shoppinglist.data.network

import com.example.shoppinglist.data.network.model.CryptoItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("coins/markets")
    suspend fun getCrypto(
        @Query("vs_currency") currency: String = "usd",
    ): List<CryptoItemDto>
}