package org.sirekanyan.`fun`.model

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import java.util.UUID

class AppState(initialScreen: AppScreen) {
    var screen by mutableStateOf<AppScreen>(initialScreen)
    val isHome by derivedStateOf { screen is HomeScreen }
}

sealed class AppScreen {
    companion object {
        private val uuidRegex = Regex("\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")
        fun createByUri(uri: String?): AppScreen {
            val value = uri.orEmpty().removePrefix("/fun").trim('/')
            val uuid = uuidRegex.matchEntire(value)?.let { result ->
                UUID.fromString(result.groupValues.first())
            }
            return uuid?.let(::SyncScreen) ?: HomeScreen
        }
    }
}

object HomeScreen : AppScreen()

class SyncScreen(initialPeerUuid: UUID?) : AppScreen() {
    val myUuid: UUID = UUID.randomUUID()
    val peerUuid: UUID? by mutableStateOf(initialPeerUuid)
}

object AddScreen : AppScreen() {
    val toolbar = ToolbarState()
}

class EditScreen(val initialItem: Item) : AppScreen() {
    var readOnly by mutableStateOf(true)
    val toolbar = ToolbarState()
}

class ToolbarState {
    val scrollState = ScrollState(initial = 0)
    val isTop by derivedStateOf { scrollState.value == 0 }
    val elevation by derivedStateOf { if (isTop) 0.dp else 3.dp }
}
