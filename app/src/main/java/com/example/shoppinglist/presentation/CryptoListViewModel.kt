package com.example.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.repository.CryptoRepositoryImpl
import com.example.shoppinglist.domain.GetCryptoItemUseCase
import com.example.shoppinglist.domain.GetCryptoListUseCase
import com.example.shoppinglist.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CryptoRepositoryImpl(application)
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