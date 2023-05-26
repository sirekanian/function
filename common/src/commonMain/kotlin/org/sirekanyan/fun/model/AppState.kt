package org.sirekanyan.`fun`.model

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AppState {
    var screen by mutableStateOf<AppScreen>(MainScreen)
    val isMain by derivedStateOf { screen is MainScreen }
}

sealed class AppScreen

object MainScreen : AppScreen()

object SyncScreen : AppScreen()
