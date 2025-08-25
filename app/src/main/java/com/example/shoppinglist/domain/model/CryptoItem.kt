package com.example.shoppinglist.domain.model

data class CryptoItem(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val imageUrl: String = "",
    val currentPrice: Double = 0.0,
    val marketCap: Long? = null,
    val marketCapRank: Int? = null,
    val totalVolume: Double? = null,
    val high24h: Double? = null,
    val low24h: Double? = null,
    val priceChange24h: Double? = null,
    val priceChangePercentage24h: Double? = null,
    val lastUpdated: String = ""
)