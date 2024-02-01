package com.khametov.effectivemobileapp.common.uikit

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
): ItemDecoration() {

    companion object {
        const val space4 = 4
        const val space8 = 8
        const val space12 = 12
        const val space16 = 16
        const val space20 = 20
        const val space24 = 24
        const val space32 = 32

        const val COLUMN_COUNT_2 = 2
        const val COLUMN_COUNT_3 = 3
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {

            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) outRect.top = spacing

            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount

            if (position >= spanCount) outRect.top = spacing
        }
    }
}