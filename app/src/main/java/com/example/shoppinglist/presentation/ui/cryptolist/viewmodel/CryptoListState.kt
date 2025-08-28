package com.example.shoppinglist.presentation.ui.cryptolist.viewmodel

import com.example.shoppinglist.domain.model.CryptoItem

sealed class CryptoListState {
    object Loading : CryptoListState()
    data class Success(val list: List<CryptoItem>) : CryptoListState()
    data class Error(val message: String) : CryptoListState()
}
