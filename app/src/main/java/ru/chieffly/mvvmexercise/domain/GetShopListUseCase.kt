package ru.chieffly.mvvmexercise.domain

import androidx.lifecycle.LiveData
import ru.chieffly.mvvmexercise.domain.model.ShopItem
import ru.chieffly.mvvmexercise.domain.repository.ShopListRepository

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun execute(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}