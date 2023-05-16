@file:JvmName("Main")

package org.sirekanyan.`fun`

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.sirekanyan.`fun`.ui.theme.DarkColorScheme

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme(DarkColorScheme) {
            App()
        }
    }
}
