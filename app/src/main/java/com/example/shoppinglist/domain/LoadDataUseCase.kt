package com.example.shoppinglist.domain

class LoadDataUseCase(private val cryptoRepository: CryptoRepository) {

    suspend operator fun invoke(): Unit = cryptoRepository.loadData()
}