package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.model.CryptoResult
import com.example.shoppinglist.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow

class GetCryptoListUseCase(private val cryptoRepository: CryptoRepository) {

    operator fun invoke(): Flow<CryptoResult> = cryptoRepository.getCryptoList()
}