package org.sirekanyan.`fun`.model

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.UUID

class AppState(initialScreen: AppScreen) {
    var screen by mutableStateOf<AppScreen>(initialScreen)
    val isMain by derivedStateOf { screen is MainScreen }
}

sealed class AppScreen {
    companion object {
        private val uuidRegex = Regex("\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")
        fun createByUri(uri: String?): AppScreen {
            val value = uri.orEmpty().removePrefix("/fun").trim('/')
            val uuid = uuidRegex.matchEntire(value)?.let { result ->
                UUID.fromString(result.groupValues.first())
            }
            return uuid?.let(::SyncScreen) ?: MainScreen
        }
    }
}

object MainScreen : AppScreen()

class SyncScreen(initialPeer: UUID?) : AppScreen() {
    var peer by mutableStateOf(initialPeer)
}

object EditScreen : AppScreen()
