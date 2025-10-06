package com.example.shoppinglist.di

import android.content.Context
import com.example.shoppinglist.data.database.AppDatabase
import com.example.shoppinglist.data.database.CryptoInfoDao
import com.example.shoppinglist.data.network.ApiFactory
import com.example.shoppinglist.data.network.ApiService
import com.example.shoppinglist.data.repository.CryptoRepositoryImpl
import com.example.shoppinglist.domain.repository.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun bindCryptoRepository(impl: CryptoRepositoryImpl): CryptoRepository

    companion object {

        @Provides
        fun provideCryptoInfoDao(
            context: Context
        ): CryptoInfoDao {
            return AppDatabase.getInstance(context).cryptoInfoDao()
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}