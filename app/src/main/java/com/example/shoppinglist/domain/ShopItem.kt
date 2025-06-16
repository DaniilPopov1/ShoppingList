package com.example.shoppinglist.domain

data class ShopItem(
    var id: Int = UNDEF_ID,
    val name: String,
    val count: Int,
    val enabled: Boolean
) {
    companion object{
        const val UNDEF_ID = -1
    }
}

