package com.khametov.effectivemobileapp.common.uikit

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.khametov.effectivemobileapp.common.extension.toDp


class OffsetDecorator(
    private val offsets: Offsets
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val isLastItem = parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)
        val isFirsItem = parent.getChildAdapterPosition(view) == 0

        outRect.run {

            when {
                (isLastItem.not() and isFirsItem.not()) -> {
                    left = offsets.left.toDp
                    top = offsets.top.toDp
                    right = offsets.right.toDp
                    bottom = offsets.bottom.toDp
                }
                (isFirsItem) -> {
                    right = offsets.right.toDp
                    bottom = offsets.bottom.toDp
                }
                (isLastItem) -> {
                    left = offsets.left.toDp
                    top = offsets.top.toDp
                }

            }


        }
    }


    class Offsets(
        val left: Int = 0,
        val top: Int = 0,
        val right: Int = 0,
        val bottom: Int = 0,
    ) {

        constructor(horizontal: Int = 0, vertical: Int = 0) : this(
            left = horizontal,
            right = horizontal,
            top = vertical,
            bottom = vertical
        )

        constructor(horizontal: Int = 0, top: Int = 0, bottom: Int = 0) : this(
            left = horizontal,
            right = horizontal,
            top = top,
            bottom = bottom
        )

        constructor(all: Int) : this(
            left = all,
            right = all,
            top = all,
            bottom = all
        )

        companion object {
            const val space4 = 4
            const val space8 = 8
            const val space12 = 12
            const val space16 = 16
            const val space20 = 20
            const val space24 = 24
            const val space32 = 32
        }
    }
}