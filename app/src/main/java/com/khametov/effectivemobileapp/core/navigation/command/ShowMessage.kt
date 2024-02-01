package com.khametov.effectivemobileapp.core.navigation.command

import com.github.terrakok.cicerone.Command
import com.khametov.effectivemobileapp.core.navigation.bundle.MessageBundle

/**
 * Команда для показа сообщений
 */
data class ShowMessage(val messageBundle: MessageBundle) : Command
