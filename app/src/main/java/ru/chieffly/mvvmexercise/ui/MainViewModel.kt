package ru.chieffly.mvvmexercise.ui

import androidx.lifecycle.LiveData
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

    val shopListLiveData = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.execute()
        shopListLiveData.value = list
    }
}