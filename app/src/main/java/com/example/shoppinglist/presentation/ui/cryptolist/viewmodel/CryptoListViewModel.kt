package com.example.shoppinglist.presentation.ui.cryptolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.repository.CryptoRepositoryImpl
import com.example.shoppinglist.domain.usecase.GetCryptoItemUseCase
import com.example.shoppinglist.domain.usecase.GetCryptoListUseCase
import com.example.shoppinglist.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch

class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CryptoRepositoryImpl(application.applicationContext)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getCryptoListUseCase = GetCryptoListUseCase(repository)
    private val getCryptoItemUseCase = GetCryptoItemUseCase(repository)

    val cryptoList = getCryptoListUseCase()

    fun getCryptoItem(id: String) = getCryptoItemUseCase(id)
    init{
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}