package com.example.shoppinglist.di

import android.app.Application
import com.example.shoppinglist.presentation.ui.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import jakarta.inject.Singleton

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        AppModule::class
    ]
)

@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

    fun viewModelFactory(): ViewModelFactory
}