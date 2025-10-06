package com.example.shoppinglist.presentation.ui.cryptolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.domain.model.CryptoResult
import com.example.shoppinglist.domain.repository.CryptoRepository
import com.example.shoppinglist.domain.usecase.GetCryptoListUseCase
import com.example.shoppinglist.domain.usecase.LoadDataUseCase
import com.example.shoppinglist.domain.usecase.RefreshDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CryptoListViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase,
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val refreshDataUseCase: RefreshDataUseCase
): ViewModel() {

    private val _cryptoListState = MutableStateFlow(
        CryptoListState(screenState = ScreenState.LOADING)
    )
    val cryptoListState: StateFlow<CryptoListState> = _cryptoListState

    init {
        viewModelScope.launch {
            loadDataUseCase()
            getCryptoListUseCase.invoke().collect { result ->
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
            _cryptoListState.update {
                it.copy(screenState = ScreenState.LOADING)
            }
            refreshDataUseCase()
        }
    }
}