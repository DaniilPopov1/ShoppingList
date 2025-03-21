package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem
import java.util.List

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enable = shopItem.enable
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enable = shopItemDbModel.enable
    )

    fun mapListDbModelToListEntity(list: @JvmSuppressWildcards List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}