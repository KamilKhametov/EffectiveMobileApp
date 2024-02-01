package com.khametov.effectivemobileapp.core.navigation.screen

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.Creator

/**
 * Тип экрана для открытия диалогов
 */
interface DialogScreen : Screen {

    val clearContainer: Boolean get() = true
    fun createFragment(factory: FragmentFactory): DialogFragment

    companion object {
        operator fun invoke(
            key: String? = null,
            clearContainer: Boolean = true,
            fragmentCreator: Creator<FragmentFactory, DialogFragment>,
        ) = object : DialogScreen {
            override val screenKey = key ?: fragmentCreator::class.java.name
            override val clearContainer = clearContainer
            override fun createFragment(factory: FragmentFactory) = fragmentCreator.create(factory)
        }
    }
}