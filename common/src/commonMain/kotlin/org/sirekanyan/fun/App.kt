package org.sirekanyan.`fun`

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.sirekanyan.`fun`.api.FunApi
import org.sirekanyan.`fun`.appbar.HorizontalAppBar
import org.sirekanyan.`fun`.appbar.VerticalAppBar
import org.sirekanyan.`fun`.data.FunRepository
import org.sirekanyan.`fun`.dialog.AddContent
import org.sirekanyan.`fun`.dialog.EditContent
import org.sirekanyan.`fun`.model.AddScreen
import org.sirekanyan.`fun`.model.AppScreen
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.EditScreen
import org.sirekanyan.`fun`.model.HomeScreen
import org.sirekanyan.`fun`.model.SyncScreen
import org.sirekanyan.`fun`.qrcode.SyncContent
import org.sirekanyan.`fun`.ui.icons.DefaultQrCodeIcon
import org.sirekanyan.`fun`.ui.layout.WindowWidthSizeClass

@Composable
fun App(screen: AppScreen, colorScheme: ColorScheme, windowSizeClass: WindowWidthSizeClass) {
    MaterialTheme(colorScheme) {
        AppContent(screen, windowSizeClass)
    }
}

@Composable
private fun AppContent(initialScreen: AppScreen, windowSizeClass: WindowWidthSizeClass) {
    val state = remember { AppState(initialScreen) }
    val repository = remember { FunRepository() }
    val api = remember { FunApi(repository) }
    val items by remember { repository.observeLastItems() }.collectAsState(listOf())
    AppLayout(
        state = state,
        windowSizeClass = windowSizeClass,
        content = {
            when (val screen = state.screen) {
                is HomeScreen -> HomeContent(it, state, items)
                is SyncScreen -> SyncContent(state, screen, api)
                is AddScreen -> AddContent(state, screen, repository)
                is EditScreen -> EditContent(state, screen, repository)
            }
        },
        actions = {
            IconButton({ state.screen = SyncScreen(initialPeerUuid = null) }) {
                Icon(DefaultQrCodeIcon, null)
            }
        },
        fab = {
            FloatingActionButton({ state.screen = AddScreen }) {
                Icon(Icons.Default.Add, null)
            }
        }
    )
}

@Composable
private fun AppLayout(
    state: AppState,
    windowSizeClass: WindowWidthSizeClass,
    content: @Composable (PaddingValues) -> Unit,
    actions: @Composable () -> Unit,
    fab: @Composable () -> Unit,
) {
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        if (windowSizeClass == WindowWidthSizeClass.Compact) {
            content(PaddingValues(bottom = D.appBarSize))
            AnimatedVisibility(state.isHome, Modifier, slideInVertically { it / 2 }, slideOutVertically { it / 2 }) {
                Box(contentAlignment = Alignment.BottomCenter) {
                    HorizontalAppBar(actions = { actions() }, fab = { fab() })
                }
            }
        } else {
            content(PaddingValues(start = D.appBarSize))
            AnimatedVisibility(state.isHome, Modifier, slideInHorizontally(), slideOutHorizontally()) {
                Box(contentAlignment = Alignment.CenterStart) {
                    VerticalAppBar(actions = { actions() }, fab = { fab() })
                }
            }
        }
    }
}
