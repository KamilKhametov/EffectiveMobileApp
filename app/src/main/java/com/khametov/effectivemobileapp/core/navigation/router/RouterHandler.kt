package com.khametov.effectivemobileapp.core.navigation.router


import androidx.fragment.app.DialogFragment
import com.khametov.effectivemobileapp.core.navigation.bundle.MessageBundle
import com.khametov.effectivemobileapp.core.navigation.permissions.PermissionStatus


/**
 * Хендлер для системных действий, которые обрабатываются в активити
 */
interface RouterHandler {
    fun switchNavTab(position: Int)
    fun showMessage(bundle: MessageBundle)
    fun showDialog(fragment: DialogFragment)
    fun closeDialog()
    fun clearPermissions()
    fun requestPermission(
        permissions: Array<String>,
        resultListener: (List<PermissionStatus>) -> Unit,
    )
}