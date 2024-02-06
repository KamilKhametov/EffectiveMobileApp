package com.khametov.effectivemobileapp.presentation.details.ui

import android.graphics.Paint
import by.kirich1409.viewbindingdelegate.viewBinding
import com.khametov.R
import com.khametov.databinding.FragmentCatalogBinding
import com.khametov.databinding.FragmentProductDetailsBinding
import com.khametov.effectivemobileapp.base.BaseMviFragment
import com.khametov.effectivemobileapp.common.extension.uiLazy
import com.khametov.effectivemobileapp.common.extension.viewModels
import com.khametov.effectivemobileapp.common.extension.withArgs
import com.khametov.effectivemobileapp.presentation.adapter.MLConcatAdapter
import com.khametov.effectivemobileapp.presentation.catalog.domain.model.CatalogItemEntity
import com.khametov.effectivemobileapp.presentation.details.ProductDetailsFeature
import com.khametov.effectivemobileapp.presentation.details.intents.ProductDetailsViewEvent
import com.khametov.effectivemobileapp.presentation.details.intents.ProductDetailsViewState
import com.khametov.effectivemobileapp.presentation.details.ui.concatblocks.DescriptionBlockAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.concatblocks.FeedbackAndPriceBlockAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.concatblocks.SliderBlockAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.concatblocks.SpecificationBlockAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.concatblocks.StructureBlockAdapter
import com.khametov.effectivemobileapp.presentation.details.ui.concatblocks.TitleBlockAdapter
import com.khametov.effectivemobileapp.presentation.details.vm.ProductDetailsViewModel
import javax.inject.Inject

class ProductDetailsFragment: BaseMviFragment<ProductDetailsViewState, ProductDetailsViewEvent, ProductDetailsViewModel>(
    R.layout.fragment_product_details
) {

    companion object {

        private const val ARG_MODEL = "ARG_MODEL"

        fun newInstance(model: CatalogItemEntity) = ProductDetailsFragment().withArgs {
            putParcelable(ARG_MODEL, model)
        }
    }

    private val itemModel by uiLazy {
        requireArguments().getParcelable<CatalogItemEntity>(ARG_MODEL)
    }

    @Inject
    lateinit var viewModelAssistedFactory: ProductDetailsViewModel.Factory

    override val viewModel: ProductDetailsViewModel by viewModels {
        viewModelAssistedFactory.create(tabRouter)
    }

    private val viewBinding by viewBinding<FragmentProductDetailsBinding>()

    private val adapter
        get() = viewBinding.productRecyclerView.adapter as MLConcatAdapter

    override fun setupInjection() {
        ProductDetailsFeature.getComponent().inject(this)
    }

    override fun setupUi() {

        viewModel.perform(ProductDetailsViewEvent.SetProductData(itemModel!!))

        with(viewBinding) {

            productRecyclerView.adapter = MLConcatAdapter(
                SliderBlockAdapter(),
                TitleBlockAdapter(),
                FeedbackAndPriceBlockAdapter(),
                DescriptionBlockAdapter(),
                SpecificationBlockAdapter(),
                StructureBlockAdapter()
            )

            priceTextView.text = getString(
                R.string.price,
                itemModel?.price?.priceWithDiscount
            )

            oldPriceTextView.text = getString(
                R.string.price,
                itemModel?.price?.price
            )

            oldPriceTextView.paintFlags =
                oldPriceTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            backImageView.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun render(state: ProductDetailsViewState) {
        renderProductDataList(state.productDataList)
    }

    private fun renderProductDataList(list: List<ProductDetailsAdapterModel>?) {
        if (list != null) {
            adapter.setItems(list)
        }
    }

    override fun onBackPressed(): Boolean {
        viewModel.perform(ProductDetailsViewEvent.OnBackPressed)
        return true
    }
}