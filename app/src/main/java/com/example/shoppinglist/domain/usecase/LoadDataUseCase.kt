package com.example.shoppinglist.domain.usecase

import com.example.shoppinglist.domain.repository.CryptoRepository

class LoadDataUseCase(private val cryptoRepository: CryptoRepository) {

    suspend operator fun invoke(): Unit = cryptoRepository.loadData()
}