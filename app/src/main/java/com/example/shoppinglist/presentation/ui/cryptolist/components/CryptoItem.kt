package com.example.shoppinglist.presentation.ui.cryptolist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppinglist.domain.model.CryptoItem

@Composable
fun CryptoItem(crypto: CryptoItem, onClick: () -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
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
            Column() {
                Text(crypto.name, fontSize = 22.sp)
                Text("Стоимость: ${crypto.currentPrice} $", fontSize = 16.sp)
                Text("Процент изменения цены: ${crypto.priceChangePercentage24h} %", fontSize = 16.sp)
            }
        }
    }
}