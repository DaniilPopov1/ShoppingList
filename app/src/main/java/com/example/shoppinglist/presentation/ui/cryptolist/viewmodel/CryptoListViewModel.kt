package com.example.shoppinglist.presentation.ui.cryptolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.repository.CryptoRepositoryImpl
import com.example.shoppinglist.domain.usecase.GetCryptoItemUseCase
import com.example.shoppinglist.domain.usecase.GetCryptoListUseCase
import com.example.shoppinglist.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CryptoRepositoryImpl(application.applicationContext)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getCryptoListUseCase = GetCryptoListUseCase(repository)
    private val getCryptoItemUseCase = GetCryptoItemUseCase(repository)

    private val _cryptoListState = MutableStateFlow<CryptoListState>(CryptoListState.Loading)
    val cryptoListState: StateFlow<CryptoListState> = _cryptoListState


    fun getCryptoItem(id: String) = getCryptoItemUseCase(id)
    init{
        viewModelScope.launch {
            try {
                launch { loadDataUseCase() }

                getCryptoListUseCase().collect { list ->
                    if (list.isEmpty()) {
                        _cryptoListState.value = CryptoListState.Loading
                    } else {
                        _cryptoListState.value = CryptoListState.Success(list)
                    }
                }
            } catch (e: Exception) {
                _cryptoListState.value = CryptoListState.Error(e.message ?: "Ошибка")
            }
        }
    }
}