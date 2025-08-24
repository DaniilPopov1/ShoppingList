package com.example.shoppinglist.data.mapper

import com.example.shoppinglist.data.database.model.CryptoItemDbModel
import com.example.shoppinglist.data.network.model.CryptoItemDto
import com.example.shoppinglist.domain.CryptoItem

class CryptoMapper {
    fun mapDtoToDbModel(dto: CryptoItemDto) = CryptoItemDbModel(
        id = dto.id,
        name = dto.name,
        symbol = dto.symbol,
        currentPrice = dto.current_price,
        imageUrl = dto.image,
        marketCap = dto.market_cap,
        marketCapRank = dto.market_cap_rank,
        totalVolume = dto.total_volume,
        high24h = dto.high_24h,
        low24h = dto.low_24h,
        priceChange24h = dto.price_change_24h,
        priceChangePercentage24h = dto.price_change_percentage_24h,
        lastUpdated = dto.last_updated
    )

    fun mapDbModelToEntity(dbModel: CryptoItemDbModel) = CryptoItem(
        id = dbModel.id,
        name = dbModel.name,
        symbol = dbModel.symbol,
        currentPrice = dbModel.currentPrice,
        imageUrl = dbModel.imageUrl,
        marketCap = dbModel.marketCap,
        marketCapRank = dbModel.marketCapRank,
        totalVolume = dbModel.totalVolume,
        high24h = dbModel.high24h,
        low24h = dbModel.low24h,
        priceChange24h = dbModel.priceChange24h,
        priceChangePercentage24h = dbModel.priceChangePercentage24h,
        lastUpdated = dbModel.lastUpdated
    )
}