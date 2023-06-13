package ru.chieffly.mvvmexercise.domain

import ru.chieffly.mvvmexercise.domain.model.ShopItem
import ru.chieffly.mvvmexercise.domain.repository.ShopListRepository

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend operator fun invoke(item: ShopItem) {
        shopListRepository.addShopItem(item)
    }

}