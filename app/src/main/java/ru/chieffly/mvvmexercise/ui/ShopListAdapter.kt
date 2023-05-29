package ru.chieffly.mvvmexercise.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.chieffly.mvvmexercise.R
import ru.chieffly.mvvmexercise.domain.model.ShopItem

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListener: OnShopItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType){
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.onShopItemOnClick(shopItem)
            true
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled)
            VIEW_TYPE_ENABLED
        else
            VIEW_TYPE_DISABLED
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    interface OnShopItemLongClickListener {
        fun onShopItemOnClick(shopItem: ShopItem)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 901
        const val VIEW_TYPE_DISABLED = 900
    }
}