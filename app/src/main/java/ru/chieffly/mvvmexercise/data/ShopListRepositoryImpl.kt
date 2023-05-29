package ru.chieffly.mvvmexercise.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.chieffly.mvvmexercise.domain.model.ShopItem
import ru.chieffly.mvvmexercise.domain.repository.ShopListRepository
import kotlin.random.Random

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = sortedSetOf<ShopItem>(Comparator { o1, o2 -> o1.id.compareTo(o2.id) })
    private val shopListLd = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 0

    init {
        for (i in 0..100){
            val item = ShopItem(name = "Name $i", count = i, enabled = Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID)
            shopItem.id = autoIncrementId++
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        shopList.remove(oldItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException(" Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLd
    }

    private fun updateList() {
        shopListLd.value = shopList.toList()
    }
}