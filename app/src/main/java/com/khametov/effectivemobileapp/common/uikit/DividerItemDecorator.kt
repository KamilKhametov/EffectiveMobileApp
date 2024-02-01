package com.khametov.effectivemobileapp.common.uikit

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class DividerItemDecorator(@ColorInt color: Int, width: Float) : ItemDecoration() {
    private val mPaint: Paint = Paint()
    private val mAlpha: Int
    private val color: Int

    init {
        mPaint.color = color
        this.color = color
        mPaint.strokeWidth = width
        mAlpha = mPaint.alpha
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val params = view.layoutParams as RecyclerView.LayoutParams

        // получаем позицию в списке
        val position = params.absoluteAdapterPosition

        // добавить место для разделителя внизу каждого вида, кроме последнего
        if (position < state.itemCount) {
            outRect[0, 0, 0] = mPaint.strokeWidth.toInt() // left, top, right, bottom
        } else {
            outRect.setEmpty()
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // линия будет отрисовываться вдвое меньше сверху и снизу,
        // следовательно, смещение для правильного размещения
        val offset = (mPaint.strokeWidth / 2).toInt()

        // перебираем каждый item
        for (i in 0 until parent.childCount-1) {
            val view = parent.getChildAt(i)
            val params = view.layoutParams as RecyclerView.LayoutParams

            // получить позицию
            val position = params.absoluteAdapterPosition

            // рисуем разделитель

            if (position < state.itemCount) {
                // применяем альфа-канал для анимации
                mPaint.alpha = (view.alpha * mAlpha).toInt()
                val positionY = view.bottom + offset + view.translationY
                // рисуем линию
                c.drawLine(
                    view.left + view.translationX,
                    positionY,
                    view.right + view.translationX,
                    positionY,
                    mPaint
                )
            }
        }
    }
}