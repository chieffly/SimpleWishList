package ru.chieffly.mvvmexercise.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *Created by Bryantsev Anton on 22.05.2023.
 **/

@Entity(tableName = "shop_item")
data class ShopItemDbModel(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    @PrimaryKey(autoGenerate = true)
    var id: Int
)