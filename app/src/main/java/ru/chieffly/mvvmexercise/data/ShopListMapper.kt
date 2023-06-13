package ru.chieffly.mvvmexercise.data

import ru.chieffly.mvvmexercise.domain.model.ShopItem

/**
 *Created by Bryantsev Anton on 13.06.2023.
 **/

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        shopItem.name,
        shopItem.count,
        shopItem.enabled,
        shopItem.id
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        shopItemDbModel.name,
        shopItemDbModel.count,
        shopItemDbModel.enabled,
        shopItemDbModel.id
    )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>): List<ShopItem> =
        list.map {
            mapDbModelToEntity(it)
        }
}