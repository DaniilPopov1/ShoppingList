package com.example.shoppinglist.data.mapper

import com.example.shoppinglist.data.database.model.CryptoItemDbModel
import com.example.shoppinglist.data.network.model.CryptoItemDto
import com.example.shoppinglist.domain.model.CryptoItem

fun CryptoItemDto.toCryptoItemDbModel(): CryptoItemDbModel {
    return CryptoItemDbModel(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.current_price,
        imageUrl = this.image,
        marketCap = this.market_cap,
        marketCapRank = this.market_cap_rank,
        totalVolume = this.total_volume,
        high24h = this.high_24h,
        low24h = this.low_24h,
        priceChange24h = this.price_change_24h,
        priceChangePercentage24h = this.price_change_percentage_24h,
        lastUpdated = this.last_updated
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