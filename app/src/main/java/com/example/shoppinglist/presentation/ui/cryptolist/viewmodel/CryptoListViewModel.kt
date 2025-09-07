package com.example.shoppinglist.presentation.ui.cryptolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.repository.CryptoRepositoryImpl
import com.example.shoppinglist.domain.model.CryptoResult
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
            launch { loadDataUseCase() }

            getCryptoListUseCase().collect { result ->
                when (result) {
                    is CryptoResult.Success -> {
                        _cryptoListState.update { state ->
                            state.copy(
                                screenState = ScreenState.SUCCESS,
                                cryptoList = result.data)
                        }
                    }
                    is CryptoResult.Error -> {
                        _cryptoListState.update { state ->
                            state.copy(
                                screenState = ScreenState.ERROR,
                                errorMessage = result.message
                            )
                        }
                    }
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _cryptoListState.update { it.copy(screenState = ScreenState.LOADING) }
            repository.refreshData()
        }
    }
}