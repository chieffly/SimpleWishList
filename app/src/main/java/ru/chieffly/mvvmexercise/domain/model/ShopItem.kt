package ru.chieffly.mvvmexercise.domain.model

/**
 *Created by Bryantsev Anton on 29.05.2023.
 **/

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID
)
{
    companion object {
        const val UNDEFINED_ID = 0
    }
}
