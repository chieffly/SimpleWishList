package ru.chieffly.mvvmexercise.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.chieffly.mvvmexercise.data.ShopListRepositoryImpl
import ru.chieffly.mvvmexercise.domain.DeleteShopItemUseCase
import ru.chieffly.mvvmexercise.domain.EditShopItemUseCase
import ru.chieffly.mvvmexercise.domain.GetShopListUseCase
import ru.chieffly.mvvmexercise.domain.model.ShopItem

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopListLiveData = getShopListUseCase.execute()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.execute(shopItem = shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.execute(newItem)
    }
}