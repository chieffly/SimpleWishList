package ru.chieffly.mvvmexercise

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.chieffly.mvvmexercise.domain.model.ShopItem
import ru.chieffly.mvvmexercise.ui.MainViewModel
import ru.chieffly.mvvmexercise.ui.ShopListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopListLiveData.observe(this) {
            Log.d("TAG", it.toString())
            shopListAdapter.shopList = it
        }
    }


    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        shopListAdapter = ShopListAdapter()
        rvShopList.adapter = shopListAdapter

        shopListAdapter.onShopItemLongClickListener = object : ShopListAdapter.OnShopItemLongClickListener {
            override fun onShopItemOnClick(shopItem: ShopItem) {
                viewModel.changeEnableState(shopItem)
            }
        }
    }
}