package com.example.shoppinglist.presentation.ui.cryptolist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shoppinglist.presentation.ui.cryptolist.components.CryptoItem
import com.example.shoppinglist.presentation.ui.cryptolist.viewmodel.CryptoListViewModel

@Composable
fun CryptoListScreen(navController: NavController,
                     vm: CryptoListViewModel = viewModel()){
    val cryptoList by vm.cryptoList.collectAsState(initial = emptyList())
    LazyColumn(Modifier.fillMaxWidth()) {
        items(cryptoList){ crypto ->
            CryptoItem(crypto) {
                navController.navigate("detail/${crypto.id}")
            }
        }
    }
}