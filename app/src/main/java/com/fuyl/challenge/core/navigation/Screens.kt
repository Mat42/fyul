package com.fuyl.challenge.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object ProductList : Screen
}
