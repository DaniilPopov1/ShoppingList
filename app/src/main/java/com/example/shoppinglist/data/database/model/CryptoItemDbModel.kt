package com.example.shoppinglist.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_info")
data class CryptoItemDbModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String,
    val imageUrl: String,
    val currentPrice: Double,
    val marketCap: Long?,
    val marketCapRank: Int?,
    val totalVolume: Double?,
    val high24h: Double?,
    val low24h: Double?,
    val priceChange24h: Double?,
    val priceChangePercentage24h: Double?,
    val lastUpdated: String
)