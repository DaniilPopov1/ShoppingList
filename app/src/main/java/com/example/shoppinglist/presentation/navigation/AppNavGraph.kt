package com.example.shoppinglist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppinglist.presentation.ui.cryptolist.CryptoListScreen
import com.example.shoppinglist.presentation.ui.detail.DetailScreen

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = AppRoutes.CryptoList.route
    ) {
        composable(AppRoutes.CryptoList.route) {
            CryptoListScreen(navController)
        }
        composable(AppRoutes.Detail.route) { backStackEntry ->
            val cryptoId = backStackEntry.arguments?.getString("cryptoId")
            DetailScreen(cryptoId)
        }
    }
}