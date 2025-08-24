package com.example.shoppinglist.domain

import kotlinx.coroutines.flow.Flow

class GetCryptoListUseCase(private val cryptoRepository: CryptoRepository) {

    operator fun invoke(): Flow<List<CryptoItem>> = cryptoRepository.getCryptoList()
}