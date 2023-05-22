package ru.chieffly.mvvmexercise.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 *Created by Bryantsev Anton on 22.05.2023.
 **/
@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_items")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query ("DELETE FROM shop_items WHERE id=:shopItemId")
    fun deleteShopItem(shopItemId: Int)
}