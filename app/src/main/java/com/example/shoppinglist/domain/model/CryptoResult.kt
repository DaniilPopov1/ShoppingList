package com.example.shoppinglist.domain.model

sealed class CryptoResult {
    data class Success(val data: List<CryptoItem>) : CryptoResult()
    data class Error(val message: String) : CryptoResult()
}