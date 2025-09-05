package com.example.shoppinglist.presentation.ui.detail.viewmodel

import com.example.shoppinglist.domain.model.CryptoItem
import com.example.shoppinglist.presentation.ui.cryptolist.viewmodel.ScreenState

data class DetailState(
    val screenState: ScreenState,
    val cryptoItem: CryptoItem? = null,
    val errorMessage: String? = null
)
