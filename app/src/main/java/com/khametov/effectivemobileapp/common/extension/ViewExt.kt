package com.khametov.effectivemobileapp.common.extension

import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Build
import android.transition.AutoTransition
import android.transition.Slide
import android.transition.TransitionManager
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import kotlin.math.roundToInt


private val displayMetrics: DisplayMetrics
    get() = Resources.getSystem().displayMetrics

val Int.toDp: Int
    get() = (this * displayMetrics.density).roundToInt()

val Int.toPx: Int
    get() = (this / displayMetrics.density).roundToInt()

val screenWidth: Int
    get() = displayMetrics.widthPixels

val screenHeight: Int
    get() = displayMetrics.heightPixels

fun ratioWidthInPercent(percent: Int): Int {
    return (screenWidth / 100) * percent
}

fun ratioHeightInPercent(percent: Int): Int {
    return (screenHeight / 100) * percent
}

fun View.withAutoTransition(container: ViewGroup? = null, block: View.() -> Unit) {
    val viewGroup = container ?: this as? ViewGroup ?: return
    TransitionManager.beginDelayedTransition(viewGroup, AutoTransition())
    block(this)
}

fun View.fadOutAnimation(
    duration: Long = 300, visibility: Int = View.INVISIBLE, completion: (() -> Unit)? = null
) {
    animate().alpha(0f).setDuration(duration).withEndAction {
        this.visibility = visibility
        completion?.let {
            it()
        }
    }
}

fun View.fadInAnimation(
    duration: Long = 300, completion: (() -> Unit)? = null
) {
    alpha = 0f
    visibility = View.VISIBLE
    animate().alpha(1f).setDuration(duration).withEndAction {
        completion?.let {
            it()
        }
    }
}

fun View.startTopAnimation(visibility: Boolean, durationTime: Long? = DEFAULT_ANIMATE_TIME) {
    val transition = Slide(Gravity.TOP)
    transition.apply {
        duration = durationTime ?: 0L
        addTarget(this@startTopAnimation)
    }
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
    this.isVisible(visibility)
}

inline fun ViewGroup.executeWithDelayedTransition(block: () -> Unit) {
    androidx.transition.TransitionManager.beginDelayedTransition(
        this,
        androidx.transition.AutoTransition()
    )
    block.invoke()
}

fun View.isVisibleWithAnimation(visibility: Boolean) {
    if (visibility) this.fadInAnimation() else this.fadOutAnimation()
}

fun TextView.setTextAnimation(
    text: String, duration: Long = 200, completion: (() -> Unit)? = null
) {
    fadOutAnimation(duration) {
        this.text = text
        fadInAnimation(duration) {
            completion?.let {
                it()
            }
        }
    }
}

fun ViewPager2.nextPage(smoothScroll: Boolean = true) {
    val nextPageIndex = currentItem + 1
    setCurrentItem(nextPageIndex, smoothScroll)
}

fun ViewPager2.prevPage(smoothScroll: Boolean = true) {
    val nextPageIndex = currentItem - 1
    setCurrentItem(nextPageIndex, smoothScroll)
}

fun EditText.cursorDrawable(@DrawableRes drawableRes: Int) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        textCursorDrawable = ContextCompat.getDrawable(context, drawableRes)
    } else {
        val field = TextView::class.java.getDeclaredField("mCursorDrawableRes")
        field.isAccessible = true
        field.set(this, drawableRes)
    }
}

fun TabLayout.disableClicking() {
    val tabStrip = this.getChildAt(0) as LinearLayout
    for (i in 0 until tabStrip.childCount) {
        tabStrip.getChildAt(i).setOnTouchListener { _, _ -> true }
    }
}

internal fun View.makeVisible() {
    visibility = View.VISIBLE
}

internal fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

internal fun View.makeGone() {
    visibility = View.GONE
}

internal fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.isVisibleKeyboard(isVisible: Boolean) {
    if (isVisible) context.showKeyboard(this) else context.hideKeyboard(this)
}

private const val DEFAULT_ANIMATE_TIME = 300L

fun NestedScrollView.scrollToPosition(position: Int) {
    this.post {
        this.fling(position)
        this.smoothScrollTo(position, position)
    }
}

fun AppCompatEditText.setPhoneMask() {
    val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)

    val formatWatcher: FormatWatcher = MaskFormatWatcher(mask)
    formatWatcher.installOn(this)
}

fun TextView.setPhoneMaskForTextView() {
    val formatWatcher: FormatWatcher =
        MaskFormatWatcher(MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER))
    formatWatcher.installOn(this)
}