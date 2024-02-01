package com.khametov.effectivemobileapp.core.navigation.command

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Screen

/**
 * Переход на следующий фрагмент с удалением текущего из стека
 */
data class ForwardWithRemoveCurrent(val screen: Screen) : Command
