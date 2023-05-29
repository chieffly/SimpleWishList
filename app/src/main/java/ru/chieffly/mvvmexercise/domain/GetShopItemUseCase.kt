package ru.chieffly.mvvmexercise.domain

import ru.chieffly.mvvmexercise.domain.model.ShopItem
import ru.chieffly.mvvmexercise.domain.repository.ShopListRepository

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun execute(itemId: Int): ShopItem {
        return shopListRepository.getShopItem(itemId)
    }
}