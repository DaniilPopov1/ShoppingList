package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.repository.CryptoRepository
import jakarta.inject.Inject

class RefreshDataUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {

    suspend operator fun invoke(): Unit = cryptoRepository.refreshData()
}