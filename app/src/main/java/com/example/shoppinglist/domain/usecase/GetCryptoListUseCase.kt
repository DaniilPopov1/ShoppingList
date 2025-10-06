package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.model.CryptoResult
import com.example.shoppinglist.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCryptoListUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {

    operator fun invoke(): Flow<CryptoResult> = cryptoRepository.getCryptoList()
}