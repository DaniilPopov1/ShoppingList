package com.example.shoppinglist.presentation.ui.cryptolist.viewmodel

import com.example.shoppinglist.domain.model.CryptoItem

enum class ScreenState {
    LOADING, SUCCESS, ERROR
}

data class CryptoListState(
    val screenState: ScreenState,
    val cryptoList: List<CryptoItem> = emptyList(),
    val errorMessage: String? = null
)

