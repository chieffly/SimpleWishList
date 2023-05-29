package ru.chieffly.mvvmexercise.domain

import ru.chieffly.mvvmexercise.domain.model.ShopItem
import ru.chieffly.mvvmexercise.domain.repository.ShopListRepository

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun execute (shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}