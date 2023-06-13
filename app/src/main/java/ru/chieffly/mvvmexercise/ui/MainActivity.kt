package ru.chieffly.mvvmexercise.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.chieffly.mvvmexercise.R
import ru.chieffly.mvvmexercise.databinding.ActivityMainBinding
import ru.chieffly.mvvmexercise.ui.main.MainViewModel
import ru.chieffly.mvvmexercise.ui.shopItem.ShopItemActivity
import ru.chieffly.mvvmexercise.ui.shopItem.ShopItemFragment
import ru.chieffly.mvvmexercise.ui.shopItem.ShopListAdapter
import ru.chieffly.mvvmexercise.ui.shopItem.ShopListAdapter.Companion.MAX_POOL_SIZE
import ru.chieffly.mvvmexercise.ui.shopItem.ShopListAdapter.Companion.VIEW_TYPE_DISABLED
import ru.chieffly.mvvmexercise.ui.shopItem.ShopListAdapter.Companion.VIEW_TYPE_ENABLED

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private lateinit var binding: ActivityMainBinding
    private fun isOnePaneMode() = binding.shopItemContainer == null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopListLiveData.observe(this) {
            shopListAdapter.submitList(it)
        }
        binding.btnAddShopItem.setOnClickListener {
            if (isOnePaneMode()){
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment (ShopItemFragment.newInstanceAddItem())
            }
        }
    }


    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        with (binding.rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                VIEW_TYPE_ENABLED,
                MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                VIEW_TYPE_DISABLED,
                MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(binding.rvShopList)
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListenerLambda = {
            viewModel.changeEnableState(it)
        }
    }

    private fun setupClickListener() {
        shopListAdapter.onShopItemClickListener = {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    override fun onEditFinished() {
        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }
}