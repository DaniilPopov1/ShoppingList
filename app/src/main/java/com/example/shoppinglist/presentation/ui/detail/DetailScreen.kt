package com.example.shoppinglist.presentation.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import com.example.shoppinglist.domain.model.CryptoItem
import com.example.shoppinglist.presentation.ui.cryptolist.viewmodel.CryptoListViewModel
import java.text.NumberFormat
import java.util.Locale

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