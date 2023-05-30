package ru.chieffly.mvvmexercise.ui.shopItem

import androidx.recyclerview.widget.DiffUtil
import ru.chieffly.mvvmexercise.domain.model.ShopItem

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}