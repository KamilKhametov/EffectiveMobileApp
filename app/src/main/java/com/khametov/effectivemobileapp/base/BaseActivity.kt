package com.khametov.effectivemobileapp.base

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.strictmode.FragmentStrictMode
import androidx.viewbinding.BuildConfig
import com.khametov.effectivemobileapp.core.navigation.permissions.PermissionStatus
import com.khametov.effectivemobileapp.core.navigation.router.RouterHandler
import com.khametov.effectivemobileapp.core.navigation.router.RouterProvider

abstract class BaseActivity(@LayoutRes layoutRes: Int): AppCompatActivity(layoutRes),
    RouterHandler, RouterProvider {

    private var currentDialogTag: String? = null
    private var permissionResultCallback: ((List<PermissionStatus>) -> Unit)? = null

    private val permissionResult = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions -> checkPermissions(permissions = permissions) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            setupStrictMode()
        }
    }

    override fun showDialog(fragment: DialogFragment) {

        val tag = buildString {
            append(fragment::class.java.simpleName)
            append(System.currentTimeMillis() / MILLIS_DIVIDER)
        }

        currentDialogTag = tag

        fragment.show(supportFragmentManager, tag)
    }

    override fun closeDialog() {

        currentDialogTag ?: return

        val fragment = supportFragmentManager.findFragmentByTag(currentDialogTag) ?: run {
            currentDialogTag = null
            return
        }

        currentDialogTag = null

        if (fragment is DialogFragment) {
            fragment.dismiss()
        }
    }

    override fun requestPermission(
        permissions: Array<String>,
        resultListener: (List<PermissionStatus>) -> Unit,
    ) {
        permissionResultCallback = resultListener
        permissionResult.launch(permissions)
    }

    override fun clearPermissions() {
        permissionResultCallback = null
    }

    private fun checkPermissions(permissions: Map<String, Boolean>) {

        val status = permissions.entries.map { (name, isGranted) ->
            when {
                isGranted                                  -> PermissionStatus.Granted
                shouldShowRequestPermissionRationale(name) -> PermissionStatus.ShowRationale(
                    permissionName = name
                )
                else                                       -> PermissionStatus.Denied(permissionName = name)
            }
        }
        permissionResultCallback?.invoke(status)
        permissionResultCallback = null
    }

    private fun setupStrictMode() {

        supportFragmentManager.strictModePolicy = FragmentStrictMode.Policy.Builder()
            .detectFragmentReuse()
            .detectFragmentTagUsage()
            .detectWrongFragmentContainer()
            .penaltyDeath()
            .build()

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectCustomSlowCalls()
                .detectNetwork()
                .penaltyDialog()
                .build()
        )
    }

    override fun onDestroy() {
        closeDialog()
        permissionResult.unregister()
        permissionResultCallback = null
        super.onDestroy()
    }

    private companion object {

        private const val MILLIS_DIVIDER = 1234321
    }
}