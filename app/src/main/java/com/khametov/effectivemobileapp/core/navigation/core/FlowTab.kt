package com.khametov.effectivemobileapp.core.navigation.core

import com.khametov.R

enum class FlowTab(val itemId: Int, val position: Int, val containerTag: String) {
    MAIN(
        itemId = R.id.actionTabMain,
        position = 0,
        containerTag = "MAIN_TAB_FRAGMENT"
    ),
    CATALOG(
        itemId = R.id.actionTabCatalog,
        position = 1,
        containerTag = "CATALOG_TAB_FRAGMENT"
    ),
    BASKET(
        itemId = R.id.actionTabBasket,
        position = 2,
        containerTag = "BASKET_TAB_FRAGMENT"
    ),
    SALE(
        itemId = R.id.actionTabSale,
        position = 3,
        containerTag = "SALE_TAB_FRAGMENT"
    ),
    PROFILE(
        itemId = R.id.actionTabProfile,
        position = 4,
        containerTag = "PROFILE_TAB_FRAGMENT"
    );

    companion object {

        fun getBy(itemId: Int): FlowTab {
            return values().first { itemId == it.itemId }
        }
    }
}
