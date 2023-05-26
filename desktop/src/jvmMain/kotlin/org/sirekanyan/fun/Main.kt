@file:JvmName("Main")

package org.sirekanyan.`fun`

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.sirekanyan.`fun`.ui.layout.WindowWidthSizeClass
import org.sirekanyan.`fun`.ui.theme.DarkColorScheme

fun main() = application {
    val windowState = rememberWindowState()
    Window(onCloseRequest = ::exitApplication, state = windowState, title = "Function") {
        App(DarkColorScheme, rememberWindowSizeClass(windowState))
    }
}

@Composable
fun rememberWindowSizeClass(windowState: WindowState): WindowWidthSizeClass {
    val width by derivedStateOf { windowState.size.width }
    return remember(width) { WindowWidthSizeClass.fromWidth(width) }
}
