package com.example.shoppinglist.presentation.ui.cryptolist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shoppinglist.presentation.navigation.AppRoutes
import com.example.shoppinglist.presentation.ui.cryptolist.components.CryptoItem
import com.example.shoppinglist.presentation.ui.cryptolist.viewmodel.CryptoListState
import com.example.shoppinglist.presentation.ui.cryptolist.viewmodel.CryptoListViewModel

@Composable
fun CryptoListScreen(navController: NavController,
                     vm: CryptoListViewModel = viewModel()){
    val state by vm.cryptoListState.collectAsState()

    when (state) {
        is CryptoListState.Loading -> {
            Text("Загрузка...")
        }

        is CryptoListState.Error -> {
            Text("Ошибка: ${(state as CryptoListState.Error).message}")
        }

        is CryptoListState.Success -> {
            LazyColumn(Modifier.fillMaxWidth()) {
                items((state as CryptoListState.Success).list) { crypto ->
                    CryptoItem(crypto) {
                        navController.navigate(AppRoutes.Detail.route.replace("{cryptoId}", crypto.id))
                    }
                }
            }
        }
    }
}