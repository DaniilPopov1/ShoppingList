package com.example.shoppinglist.presentation.ui.detail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.repository.CryptoRepositoryImpl
import com.example.shoppinglist.domain.usecase.GetCryptoItemUseCase
import com.example.shoppinglist.presentation.ui.cryptolist.viewmodel.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CryptoRepositoryImpl(application.applicationContext)
    private val getCryptoItemUseCase = GetCryptoItemUseCase(repository)

    private val _detailState = MutableStateFlow(
        DetailState(screenState = ScreenState.LOADING)
    )
    val detailState: StateFlow<DetailState> = _detailState

    fun loadCryptoItem(cryptoId: String) {
        viewModelScope.launch {
            try {
                getCryptoItemUseCase(cryptoId).collect { item ->
                    _detailState.update { state ->
                        state.copy(
                            screenState = ScreenState.SUCCESS,
                            cryptoItem = item
                        )
                    }
                }
            } catch (e: Exception) {
                _detailState.update { state ->
                    state.copy(
                        screenState = ScreenState.ERROR,
                        errorMessage = e.message ?: "Ошибка"
                    )
                }
            }
        }
    }
}