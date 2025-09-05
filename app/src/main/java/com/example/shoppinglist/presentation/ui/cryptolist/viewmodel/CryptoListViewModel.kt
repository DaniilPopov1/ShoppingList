package com.example.shoppinglist.presentation.ui.cryptolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.repository.CryptoRepositoryImpl
import com.example.shoppinglist.domain.usecase.GetCryptoListUseCase
import com.example.shoppinglist.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CryptoRepositoryImpl(application.applicationContext)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getCryptoListUseCase = GetCryptoListUseCase(repository)

    private val _cryptoListState = MutableStateFlow(
        CryptoListState(screenState = ScreenState.LOADING)
    )
    val cryptoListState: StateFlow<CryptoListState> = _cryptoListState

    init{
        viewModelScope.launch {
            try {
                launch { loadDataUseCase() }

                getCryptoListUseCase().collect { list ->
                    if (list.isEmpty()) {
                        _cryptoListState.update { state ->
                            state.copy(screenState = ScreenState.LOADING)
                        }
                    } else {
                        _cryptoListState.update { state ->
                            state.copy(
                                screenState = ScreenState.SUCCESS,
                                cryptoList = list
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _cryptoListState.update { state ->
                    state.copy(
                        screenState = ScreenState.ERROR,
                        errorMessage = e.message ?: "Ошибка"
                    )
                }
            }
        }
    }
}