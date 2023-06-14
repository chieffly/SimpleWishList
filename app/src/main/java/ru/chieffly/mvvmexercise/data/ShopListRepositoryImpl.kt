package ru.chieffly.mvvmexercise.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.chieffly.mvvmexercise.domain.model.ShopItem
import ru.chieffly.mvvmexercise.domain.repository.ShopListRepository

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> =
        shopListDao.getShopList().map {
            mapper.mapListDbModelToListEntity(it)
        }

}