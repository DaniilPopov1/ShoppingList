package com.example.shoppinglist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.shoppinglist.domain.CryptoItem
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "crypto_list"
            ) {
                composable("crypto_list") {
                    CryptoListScreen(navController)
                }
                composable("detail/{cryptoId}") { backStackEntry ->
                    val cryptoId = backStackEntry.arguments?.getString("cryptoId")
                    DetailScreen(cryptoId)
                }
            }
        }
    }
}

@Composable
fun CryptoListScreen(navController: NavController,
                     vm: CryptoListViewModel = viewModel()){
    val cryptoList by vm.cryptoList.collectAsState(initial = emptyList())
    LazyColumn(Modifier.fillMaxWidth()) {
        items(cryptoList){ crypto ->
            CryptoItems(crypto) {
                navController.navigate("detail/${crypto.id}")
            }
        }
    }
}

@Composable
fun CryptoItems(crypto: CryptoItem, onClick: () -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
        ){
        Row(modifier = Modifier.padding(10.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            AsyncImage(
                model = crypto.imageUrl,
                contentDescription = crypto.name,
                modifier = Modifier.size(80.dp).padding(10.dp)
            )
            Column(){
                Text(crypto.name, fontSize = 22.sp)
                Text("Стоимость: ${crypto.currentPrice} $", fontSize = 16.sp)
                Text("Процент изменения цены: ${crypto.priceChangePercentage24h} %", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DetailScreen(cryptoId: String?, vm: CryptoListViewModel = viewModel()) {

    val cryptoFlow = remember(cryptoId) { vm.getCryptoItem(cryptoId!!)  }
    val crypto by cryptoFlow.collectAsState(initial = CryptoItem())

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = crypto.imageUrl,
            contentDescription = crypto.name,
            modifier = Modifier.size(100.dp).padding(10.dp)
        )
        Text(crypto.name, fontSize = 30.sp)
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ){
            Column(Modifier.padding(10.dp).fillMaxWidth()){
                Text("Стоимость: ${crypto.currentPrice} $", fontSize = 18.sp)
                Text("Капитализация: ${crypto.marketCap} $", fontSize = 18.sp)
                Text("Рейтинг по капитализации: ${crypto.marketCapRank} $", fontSize = 18.sp)
                Text("Объем торгов: ${NumberFormat.getInstance(Locale.US).format(crypto.totalVolume ?: 0)} $", fontSize = 18.sp)
                Text("Макс за 24 часа: ${crypto.high24h} $", fontSize = 18.sp)
                Text("Мин за 24 часа: ${crypto.low24h} $", fontSize = 18.sp)
                Text("Изменение цены: ${crypto.priceChange24h} $", fontSize = 18.sp)
                Text("Процент изменения цены: ${crypto.priceChangePercentage24h} %", fontSize = 18.sp)
            }
        }
    }
}