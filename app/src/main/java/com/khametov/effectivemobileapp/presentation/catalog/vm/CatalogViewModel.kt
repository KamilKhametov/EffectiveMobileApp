package com.khametov.effectivemobileapp.presentation.catalog.vm

import com.khametov.effectivemobileapp.base.BaseViewModel
import com.khametov.effectivemobileapp.core.navigation.router.CustomRouter
import com.khametov.effectivemobileapp.core.navigation.screen.Screens
import com.khametov.effectivemobileapp.presentation.catalog.domain.repository.CatalogRepository
import com.khametov.effectivemobileapp.presentation.catalog.intents.CatalogViewEvent
import com.khametov.effectivemobileapp.presentation.catalog.intents.CatalogViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class CatalogViewModel @AssistedInject constructor(
    @Assisted("router") private val router: CustomRouter,
    private val repository: CatalogRepository
): BaseViewModel<CatalogViewState, CatalogViewEvent>(
    initialState = CatalogViewState()
) {

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("router") router: CustomRouter
        ): CatalogViewModel
    }

    override fun observe(event: CatalogViewEvent) {
        when (event) {
            is CatalogViewEvent.NavigateToDetails -> router.forward(Screens.productDetails(event.model))
        }
    }

    init {
        getCatalog()
    }

    private fun getCatalog() {

        launchCoroutine {

            val entity = repository.getCatalog()

            updateState {
                copy(
                    catalogEntity = entity
                )
            }
        }
    }
}