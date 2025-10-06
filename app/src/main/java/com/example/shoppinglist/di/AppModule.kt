package com.example.shoppinglist.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton

@Module
interface AppModule {
    companion object {
        @Provides
        fun provideContext(application: Application): Context =
            application.applicationContext
    }
}