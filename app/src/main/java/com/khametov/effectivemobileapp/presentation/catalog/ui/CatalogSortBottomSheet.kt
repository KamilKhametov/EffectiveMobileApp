package com.khametov.effectivemobileapp.presentation.catalog.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.khametov.databinding.SortBottomSheetBinding

class CatalogSortBottomSheet(
    private val selectSort: (type: Int, name: String) -> Unit
): BottomSheetDialogFragment() {

    private var _binding: SortBottomSheetBinding? = null

    private val viewBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = SortBottomSheetBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(viewBinding) {

            popularSort.setOnClickListener {
                selectSort(0, popularSort.text.toString())
                dialog?.cancel()
            }

            reducingSort.setOnClickListener {
                selectSort(1, reducingSort.text.toString())
                dialog?.cancel()
            }

            ascendingSort.setOnClickListener {
                selectSort(2, ascendingSort.text.toString())
                dialog?.cancel()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (citySelectionDialogInstance != null) {
            super.onDismiss(dialog)
            citySelectionDialogInstance = null
        }
    }

    companion object {

        private var citySelectionDialogInstance: CatalogSortBottomSheet? = null

        fun show(
            fm: FragmentManager,
            selectSort: (type: Int, name: String) -> Unit
        ) {
            if (citySelectionDialogInstance == null) {
                citySelectionDialogInstance = CatalogSortBottomSheet(selectSort)
                citySelectionDialogInstance?.show(fm, CatalogSortBottomSheet::class.simpleName)
            }
        }

        fun dismissDialog() {
            citySelectionDialogInstance?.dismiss()
        }
    }
}