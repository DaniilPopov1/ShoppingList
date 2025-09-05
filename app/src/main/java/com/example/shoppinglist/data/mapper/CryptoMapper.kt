package com.example.shoppinglist.data.mapper

import com.example.shoppinglist.data.database.model.CryptoItemDbModel
import com.example.shoppinglist.data.network.model.CryptoItemDto
import com.example.shoppinglist.domain.model.CryptoItem

fun CryptoItemDto.toCryptoItemDbModel(): CryptoItemDbModel {
    return CryptoItemDbModel(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        imageUrl = this.image,
        marketCap = this.marketCap,
        marketCapRank = this.marketCapRank,
        totalVolume = this.totalVolume,
        high24h = this.high24h,
        low24h = this.low24h,
        priceChange24h = this.priceChange24h,
        priceChangePercentage24h = this.priceChangePercentage24h,
        lastUpdated = this.lastUpdated
    )
}

fun CryptoItemDbModel.toCryptoItem(): CryptoItem{
    return CryptoItem(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.currentPrice,
        imageUrl = this.imageUrl,
        marketCap = this.marketCap,
        marketCapRank = this.marketCapRank,
        totalVolume = this.totalVolume,
        high24h = this.high24h,
        low24h = this.low24h,
        priceChange24h = this.priceChange24h,
        priceChangePercentage24h = this.priceChangePercentage24h,
        lastUpdated = this.lastUpdated
    )
}