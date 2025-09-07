package com.example.shoppinglist.presentation.ui.cryptolist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shoppinglist.presentation.navigation.AppRoutes
import com.example.shoppinglist.presentation.ui.cryptolist.components.CryptoItem
import com.example.shoppinglist.presentation.ui.cryptolist.viewmodel.CryptoListViewModel
import com.example.shoppinglist.presentation.ui.cryptolist.viewmodel.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoListScreen(navController: NavController,
                     vm: CryptoListViewModel = viewModel()){
    val state by vm.cryptoListState.collectAsState()

    PullToRefreshBox(
        isRefreshing = state.screenState == ScreenState.LOADING,
        onRefresh = { vm.refresh() },
        state = rememberPullToRefreshState()
    ) {
        when (state.screenState) {
            ScreenState.LOADING -> {
                Text(
                    text = "Загрузка...",
                    fontSize = 16.sp
                )
            }

            ScreenState.ERROR -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCDD2))
                            ) {
                                Text(
                                    text = state.errorMessage ?: "Произошла ошибка",
                                    color = Color(0xFFB00020),
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            ScreenState.SUCCESS -> {
                LazyColumn(
                    Modifier.fillMaxWidth()
                ) {
                    items((state.cryptoList)) { crypto ->
                        CryptoItem(crypto) {
                            navController.navigate(
                                AppRoutes.Detail.route.replace("{cryptoId}", crypto.id)
                            )
                        }
                    }
                }
            }
        }
    }

}