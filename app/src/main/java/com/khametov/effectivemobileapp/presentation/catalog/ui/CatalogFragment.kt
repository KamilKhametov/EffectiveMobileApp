package com.khametov.effectivemobileapp.presentation.catalog.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.khametov.R
import com.khametov.databinding.FragmentCatalogBinding
import com.khametov.effectivemobileapp.base.BaseMviFragment
import com.khametov.effectivemobileapp.common.extension.postDelayed
import com.khametov.effectivemobileapp.common.extension.uiLazy
import com.khametov.effectivemobileapp.common.extension.viewModels
import com.khametov.effectivemobileapp.presentation.catalog.CatalogFeature
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

    private var isFirstOpen = true

    private var currentTag = "all"

    private val tags = arrayListOf(
        TagModel(
            id = "1",
            name = "Смотреть все",
            type = "all",
            isClicked = true
        ),
        TagModel(
            id = "2",
            name = "Тело",
            type = "body"
        ),
        TagModel(
            id = "3",
            name = "Лицо",
            type = "face"
        ),
        TagModel(
            id = "4",
            name = "Маска",
            type = "mask"
        ),
        TagModel(
            id = "5",
            name = "Масло",
            type = "suntan"
        )
    )

    private val tagAdapter by uiLazy {
        TagsAdapter(
            onTagClick = { tagModel ->
                currentTag = tagModel.type

                changeClickedStateOnTag(tagModel.id)
                viewModel.perform(CatalogViewEvent.SortByTag(tagModel.type))
            },
            cancelSort = {
                currentTag = "all"
                changeClickedStateOnTag("")
                viewModel.perform(CatalogViewEvent.SortByTag("all"))
            }
        )
    }

    private val productsAdapter by uiLazy {
        ProductsAdapter(
            onItemClick = { model ->
                viewModel.perform(CatalogViewEvent.NavigateToDetails(model))
            },
            addToFavorite = { isAdd, model ->
                viewModel.perform(CatalogViewEvent.AddToFavorites(isAdd, model, currentTag))
            }
        )
    }

    override fun setupInjection() {
        CatalogFeature.getComponent().inject(this)
    }

    override fun setupUi() {

        with(viewBinding) {

            productsRecyclerView.itemAnimator = null

            tagsRecyclerView.adapter = tagAdapter
            productsRecyclerView.adapter = productsAdapter

            sortTextView.setOnClickListener {
                CatalogSortBottomSheet.show(
                    childFragmentManager,
                    selectSort = { type, name ->

                        sortTextView.text = name

                        viewModel.perform(CatalogViewEvent.SortProducts(type))
                    }
                )
            }
        }

        setupTags()
    }

    override fun render(state: CatalogViewState) {
        renderProducts(state.catalogItems ?: listOf())
    }

    private fun setupTags() {
        tagAdapter.submitList(tags)
    }

    private fun renderProducts(list: List<CatalogItemEntity>) {

        if (list.isNotEmpty()) {
            productsAdapter.submitList(list) {
                postDelayed(200) {
                    if (isFirstOpen) {
                        viewBinding.productsRecyclerView.layoutManager?.scrollToPosition(0)
                        isFirstOpen = false
                    }
                }
            }
        }
    }

    private fun changeClickedStateOnTag(clickedTagId: String) {

        val tagsWithClickedList = tags.map {
            if (it.id == clickedTagId) {
                it.copy(isClicked = true)
            } else {
                it.copy(isClicked = false)
            }
        }

        tagAdapter.submitList(tagsWithClickedList)
    }

    override fun onBackPressed(): Boolean {
        return true
    }
}