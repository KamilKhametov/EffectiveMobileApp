package com.khametov.effectivemobileapp.common.extension

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.khametov.R

fun Fragment.toast(textResource: Int) {
    Toast.makeText(this.requireContext(), textResource, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}

/**
 * [Extension] для вызова кастомного [MaterialAlertDialog] из фрагмента.
 * В дженейриках указываем [ViewBinding] который раздули в параметрах.
 * В теле фрагмента обращаемся к [ViewBinding] через [this]
 * для закрытия MaterialAlertDialog обращаемся к [it]
 */
fun <T: ViewBinding> Fragment.showAlertDialog(
    @LayoutRes layoutResId: Int = R.style.MaterialAlertDialog_rounded,
    viewBinding: ViewBinding,
    body: T.(dismiss: () -> Unit) -> Unit
) {
    val alertDialog = context?.let { MaterialAlertDialogBuilder(it, layoutResId) }
    val dialogViewBinding = (viewBinding as T)

    alertDialog?.setView(dialogViewBinding.root)
    alertDialog?.setCancelable(true)

    val showAlertDialog = alertDialog?.show()
    showAlertDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    showAlertDialog?.window?.setLayout(320.toDp, ConstraintLayout.LayoutParams.WRAP_CONTENT)

    if (showAlertDialog != null) {
        body.invoke(viewBinding, showAlertDialog::dismiss)
    }
}

