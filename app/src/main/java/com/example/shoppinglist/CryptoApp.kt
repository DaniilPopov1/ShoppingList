package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.di.DaggerAppComponent

class CryptoApp: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}