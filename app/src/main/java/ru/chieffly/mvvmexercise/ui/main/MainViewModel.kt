package ru.chieffly.mvvmexercise.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.chieffly.mvvmexercise.data.ShopListRepositoryImpl
import ru.chieffly.mvvmexercise.domain.DeleteShopItemUseCase
import ru.chieffly.mvvmexercise.domain.EditShopItemUseCase
import ru.chieffly.mvvmexercise.domain.GetShopListUseCase
import ru.chieffly.mvvmexercise.domain.model.ShopItem

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopListLiveData = getShopListUseCase.execute()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
        deleteShopItemUseCase(shopItem = shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase(newItem)
        }
    }

}