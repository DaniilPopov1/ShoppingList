package com.example.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.presentation.ui.cryptolist.viewmodel.CryptoListViewModel
import com.example.shoppinglist.presentation.ui.detail.viewmodel.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CryptoListViewModel::class)
    fun bindCryptoListViewModule(viewModel: CryptoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModule(viewModel: DetailViewModel): ViewModel
}