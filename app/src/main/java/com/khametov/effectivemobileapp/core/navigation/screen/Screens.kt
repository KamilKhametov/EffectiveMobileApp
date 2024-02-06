package com.khametov.effectivemobileapp.core.navigation.screen

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.khametov.effectivemobileapp.core.navigation.core.TabContainerFragment
import com.khametov.effectivemobileapp.presentation.auth.ui.AuthFragment
import com.khametov.effectivemobileapp.presentation.basket.ui.BasketFragment
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.catalog.ui.CatalogFragment
import com.khametov.effectivemobileapp.presentation.details.ui.ProductDetailsFragment
import com.khametov.effectivemobileapp.presentation.flow.ui.FlowFragment
import com.khametov.effectivemobileapp.presentation.main.ui.MainFragment
import com.khametov.effectivemobileapp.presentation.profile.ui.ProfileFragment
import com.khametov.effectivemobileapp.presentation.sale.ui.SaleFragment

object Screens {

    fun tabScreen(containerTag: String, position: Int) = FragmentScreen {
        TabContainerFragment.newInstance(containerTag = containerTag, position = position)
    }

    fun flow() = FragmentScreen {
        FlowFragment.newInstance()
    }

    fun auth() = FragmentScreen {
        AuthFragment.newInstance()
    }

    fun main() = FragmentScreen {
        MainFragment()
    }

    fun catalog() = FragmentScreen {
        CatalogFragment()
    }

    fun basket() = FragmentScreen {
        BasketFragment()
    }

    fun sale() = FragmentScreen {
        SaleFragment()
    }

    fun profile() = FragmentScreen {
        ProfileFragment()
    }

    fun productDetails(model: CatalogItemEntity) = FragmentScreen {
        ProductDetailsFragment.newInstance(model)
    }
}