package com.khametov.effectivemobileapp.presentation.catalog.ui

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.khametov.R
import com.khametov.databinding.FragmentCatalogBinding
import com.khametov.effectivemobileapp.base.BaseMviFragment
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

    private val allProductsList = arrayListOf<CatalogItemEntity>()

    private var sortPosition = 0

    private var currentTag = ""

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
                sortProductsByTag(tagModel.type)
            },
            cancelSort = {
                changeClickedStateOnTag("")
                sortProductsByTag("all")
            }
        )
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

        setupSortSpinner()
        setupTags()
    }

    private fun setupSortSpinner() {

        val sortTypes = resources.getStringArray(R.array.sort)

        with(viewBinding) {

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                sortTypes
            )

            spinner.adapter = adapter

            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                    sortPosition = position
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
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

            allProductsList.clear()
            allProductsList.addAll(viewModel.viewState().value.catalogEntity?.items ?: listOf())
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

    private fun sortProductsByTag(type: String) {

        val productsByTag =
            viewModel.viewState().value.catalogEntity?.items?.filter { catalogItemEntity ->
                catalogItemEntity.tags.any {

                    if (type == "all") {
                        productsAdapter.submitList(allProductsList)
                        return
                    }

                    it == type
                }
            }

        productsAdapter.submitList(productsByTag)
    }

    override fun onBackPressed(): Boolean {
        return true
    }
}