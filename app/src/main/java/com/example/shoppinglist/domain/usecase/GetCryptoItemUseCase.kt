package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.model.CryptoItem
import com.example.shoppinglist.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow

class GetCryptoItemUseCase(private val cryptoRepository: CryptoRepository) {

    operator fun invoke(id: String): Flow<CryptoItem> = cryptoRepository.getCryptoItem(id)
}