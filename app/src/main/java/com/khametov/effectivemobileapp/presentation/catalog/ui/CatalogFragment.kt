package com.khametov.effectivemobileapp.presentation.catalog.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.khametov.R
import com.khametov.databinding.FragmentCatalogBinding
import com.khametov.effectivemobileapp.base.BaseMviFragment
import com.khametov.effectivemobileapp.common.extension.uiLazy
import com.khametov.effectivemobileapp.common.extension.viewModels
import com.khametov.effectivemobileapp.presentation.catalog.CatalogFeature
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.TagModel
import com.khametov.effectivemobileapp.presentation.catalog.intents.CatalogViewEvent
import com.khametov.effectivemobileapp.presentation.catalog.intents.CatalogViewState
import com.khametov.effectivemobileapp.presentation.catalog.vm.CatalogViewModel
import javax.inject.Inject

class CatalogFragment:
    BaseMviFragment<CatalogViewState, CatalogViewEvent, CatalogViewModel>(R.layout.fragment_catalog) {

    @Inject
    lateinit var viewModelAssistedFactory: CatalogViewModel.Factory

    override val viewModel: CatalogViewModel by viewModels {
        viewModelAssistedFactory.create(tabRouter)
    }

    private val viewBinding by viewBinding<FragmentCatalogBinding>()

    private val tags = arrayListOf(
        TagModel(
            name = "Смотреть все",
            type = "all"
        ),
        TagModel(
            name = "Тело",
            type = "body"
        ),
        TagModel(
            name = "Лицо",
            type = "face"
        ),
        TagModel(
            name = "Маска",
            type = "mask"
        ),
        TagModel(
            name = "Масло",
            type = "suntan"
        )
    )

    private val tagAdapter by uiLazy {
        TagsAdapter()
    }

    private val productsAdapter by uiLazy {
        ProductsAdapter()
    }

    override fun setupInjection() {
        CatalogFeature.getComponent().inject(this)
    }

    override fun setupUi() {

        with(viewBinding) {

            tagsRecyclerView.adapter = tagAdapter
            productsRecyclerView.adapter = productsAdapter
        }

        setupTags()
    }

    override fun render(state: CatalogViewState) {
        renderProducts(state.catalogEntity?.items ?: listOf())
    }

    private fun setupTags() {
        tagAdapter.submitList(tags)
    }

    private fun renderProducts(list: List<CatalogItemEntity>) {

        if (list.isNotEmpty()) {
            productsAdapter.submitList(list)
        }
    }

    override fun onBackPressed(): Boolean {
        return true
    }
}