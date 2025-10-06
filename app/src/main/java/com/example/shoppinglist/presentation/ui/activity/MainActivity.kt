package com.example.shoppinglist.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.presentation.navigation.AppNavGraph
import com.example.shoppinglist.CryptoApp
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val appComponent by lazy {
        (application as CryptoApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = appComponent.viewModelFactory()

        setContent {
            val navController = rememberNavController()
            AppNavGraph(navController, factory)
        }
    }
}

