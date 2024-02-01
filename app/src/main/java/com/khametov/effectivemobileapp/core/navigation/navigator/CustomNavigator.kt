package com.khametov.effectivemobileapp.core.navigation.navigator


import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.khametov.R
import com.khametov.effectivemobileapp.core.navigation.bundle.MessageBundle
import com.khametov.effectivemobileapp.core.navigation.command.ForwardWithRemoveCurrent
import com.khametov.effectivemobileapp.core.navigation.command.RequestPermissions
import com.khametov.effectivemobileapp.core.navigation.command.RestartApp
import com.khametov.effectivemobileapp.core.navigation.command.ShowDialog
import com.khametov.effectivemobileapp.core.navigation.command.ShowMessage
import com.khametov.effectivemobileapp.core.navigation.command.SwitchTab
import com.khametov.effectivemobileapp.core.navigation.core.TransitionType
import com.khametov.effectivemobileapp.core.navigation.permissions.PermissionStatus
import com.khametov.effectivemobileapp.core.navigation.screen.DialogScreen
import com.khametov.effectivemobileapp.base.BaseActivity
import com.khametov.effectivemobileapp.base.BaseFragment

class CustomNavigator(
    private val navigatorActivity: BaseActivity,
    containerId: Int,
    fragmentManager: FragmentManager = navigatorActivity.supportFragmentManager,
): AppNavigator(
    activity = navigatorActivity,
    containerId = containerId,
    fragmentManager = fragmentManager
) {

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment,
    ) {

        val transitionType = (nextFragment as? BaseFragment)?.transitionType

        if (currentFragment == null || nextFragment == currentFragment) {
            return
        } else when (transitionType) {

            TransitionType.VERTICAL   -> {
                fragmentTransaction.setCustomAnimations(
                    R.anim.slide_in_bottom,
                    R.anim.slide_out_top,
                    R.anim.slide_in_top,
                    R.anim.slide_out_bottom
                )
            }

            TransitionType.HORIZONTAL -> {
                fragmentTransaction.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
            }

            else -> return
        }
    }

    override fun applyCommands(commands: Array<out Command>) {
        handleApplyCommands()
        super.applyCommands(commands)
    }

    override fun applyCommand(command: Command) {
        when (command) {
            is RestartApp -> handleRestartApplicationCommand()
            is ShowDialog -> handleShowDialogCommand(screen = command.screen)
            is SwitchTab -> handleSwitchTabCommand(position = command.position)
            is ShowMessage -> handleShowMessageCommand(bundle = command.messageBundle)
            is RequestPermissions       -> handleRequestPermissionsCommand(
                permissions = command.permissions,
                resultListener = command.resultListener
            )
            is ForwardWithRemoveCurrent -> handleForwardWithRemoveCurrent(screen = command.screen)
            else                        -> super.applyCommand(command)
        }
    }

    private fun handleRequestPermissionsCommand(
        permissions: Array<String>,
        resultListener: (List<PermissionStatus>) -> Unit,
    ) {
        navigatorActivity.requestPermission(permissions, resultListener = resultListener)
    }

    private fun handleApplyCommands() {
        navigatorActivity.closeDialog()
        navigatorActivity.clearPermissions()
        navigatorActivity.hideKeyboard()
    }

    private fun handleForwardWithRemoveCurrent(screen: Screen) {
        fragmentManager.beginTransaction().apply {
            remove(fragmentManager.fragments.last())
            commit()
        }
        fragmentManager.popBackStack()
        applyCommand(Forward(screen))
    }

    private fun handleShowDialogCommand(screen: DialogScreen) {
        val fragment = screen.createFragment(factory = fragmentFactory)
        navigatorActivity.showDialog(fragment = fragment)
    }

    private fun handleShowMessageCommand(bundle: MessageBundle) {
        navigatorActivity.showMessage(bundle = bundle)
    }

    private fun handleSwitchTabCommand(position: Int) {
        navigatorActivity.switchNavTab(position = position)
    }

    private fun handleRestartApplicationCommand() {

        with(navigatorActivity) {
            baseContext.packageManager
                .getLaunchIntentForPackage(packageName)
                ?.apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }?.also(::startActivity)
        }
    }

    private fun FragmentActivity.hideKeyboard(flags: Int = 0) {
        val view = currentFocus ?: View(this)
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, flags)
    }
}