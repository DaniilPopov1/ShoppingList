package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.model.CryptoItem
import com.example.shoppinglist.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow

class GetCryptoListUseCase(private val cryptoRepository: CryptoRepository) {

    operator fun invoke(): Flow<List<CryptoItem>> = cryptoRepository.getCryptoList()
}