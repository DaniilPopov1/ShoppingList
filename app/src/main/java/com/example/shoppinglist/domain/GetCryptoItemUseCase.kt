package com.example.shoppinglist.domain

import kotlinx.coroutines.flow.Flow

class GetCryptoItemUseCase(private val cryptoRepository: CryptoRepository) {

    operator fun invoke(id: String): Flow<CryptoItem> = cryptoRepository.getCryptoItem(id)
}