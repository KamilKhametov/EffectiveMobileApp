package com.khametov.effectivemobileapp.core.navigation.command

import com.github.terrakok.cicerone.Command
import com.khametov.effectivemobileapp.core.navigation.screen.DialogScreen

/**
 * Команда для показа диалогов
 */
data class ShowDialog(val screen: DialogScreen) : Command