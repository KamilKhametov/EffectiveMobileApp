package com.khametov.effectivemobileapp.presentation.favorites.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.khametov.R
import com.khametov.databinding.FragmentFavoritesBinding
import com.khametov.effectivemobileapp.base.BaseMviFragment
import com.khametov.effectivemobileapp.common.extension.uiLazy
import com.khametov.effectivemobileapp.common.extension.viewModels
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.catalog.ui.ProductsAdapter
import com.khametov.effectivemobileapp.presentation.favorites.FavoritesFeature
import com.khametov.effectivemobileapp.presentation.favorites.intents.FavoritesViewEvent
import com.khametov.effectivemobileapp.presentation.favorites.intents.FavoritesViewState
import com.khametov.effectivemobileapp.presentation.favorites.vm.FavoritesViewModel
import javax.inject.Inject

class FavoritesFragment: BaseMviFragment<FavoritesViewState, FavoritesViewEvent, FavoritesViewModel>(
    R.layout.fragment_favorites
) {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    @Inject
    lateinit var viewModelAssistedFactory: FavoritesViewModel.Factory

    override val viewModel: FavoritesViewModel by viewModels {
        viewModelAssistedFactory.create(tabRouter)
    }

    private val viewBinding by viewBinding<FragmentFavoritesBinding>()

    private val productsAdapter by uiLazy {
        ProductsAdapter(
            onItemClick = {},
            addToFavorite = { isAdd, model ->
                viewModel.perform(FavoritesViewEvent.AddToFavorites(isAdd, model))
            }
        )
    }

    override fun setupInjection() {
        FavoritesFeature.getComponent().inject(this)
    }

    override fun setupUi() {

        with(viewBinding) {

            backImageView.setOnClickListener {
                onBackPressed()
            }

            favoritesRecyclerView.adapter = productsAdapter
        }
    }

    override fun render(state: FavoritesViewState) {
        renderFavoritesData(state.favoritesData)
    }

    private fun renderFavoritesData(list: List<CatalogItemEntity>?) {
        productsAdapter.submitList(list)
    }

    override fun onBackPressed(): Boolean {
        viewModel.perform(FavoritesViewEvent.OnBackPressed)
        return true
    }
}