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
import org.sirekanyan.`fun`.appbar.HorizontalAppBar
import org.sirekanyan.`fun`.appbar.VerticalAppBar
import org.sirekanyan.`fun`.data.FunRepository
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.MainScreen
import org.sirekanyan.`fun`.model.SyncScreen
import org.sirekanyan.`fun`.qrcode.SyncContent
import org.sirekanyan.`fun`.ui.icons.DefaultQrCodeIcon
import org.sirekanyan.`fun`.ui.layout.WindowWidthSizeClass
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun App(colorScheme: ColorScheme, windowSizeClass: WindowWidthSizeClass) {
    MaterialTheme(colorScheme) {
        AppContent(windowSizeClass)
    }
}

@Composable
private fun AppContent(windowSizeClass: WindowWidthSizeClass) {
    val state = remember { AppState() }
    val repository = remember { FunRepository() }
    val items by remember { repository.observeItems() }.collectAsState(listOf())
    AppLayout(
        state = state,
        windowSizeClass = windowSizeClass,
        content = {
            when (state.screen) {
                MainScreen -> MainContent(it, repository, items)
                SyncScreen -> SyncContent(state)
            }
        },
        actions = {
            IconButton({ state.screen = SyncScreen }) {
                Icon(DefaultQrCodeIcon, null)
            }
        },
        fab = {
            FloatingActionButton(
                onClick = { repository.putContent("$platformName Item ${abs(Random.nextInt()) % 1000}") },
            ) {
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
            AnimatedVisibility(state.isMain, Modifier, slideInVertically { it / 2 }, slideOutVertically { it / 2 }) {
                Box(contentAlignment = Alignment.BottomCenter) {
                    HorizontalAppBar(actions = { actions() }, fab = { fab() })
                }
            }
        } else {
            content(PaddingValues(start = D.appBarSize))
            AnimatedVisibility(state.isMain, Modifier, slideInHorizontally(), slideOutHorizontally()) {
                Box(contentAlignment = Alignment.CenterStart) {
                    VerticalAppBar(actions = { actions() }, fab = { fab() })
                }
            }
        }
    }
}
