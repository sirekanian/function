package org.sirekanyan.`fun`.appbar

import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun AppBarSurface(content: @Composable () -> Unit) {
    Surface(
        color = BottomAppBarDefaults.containerColor,
        tonalElevation = BottomAppBarDefaults.ContainerElevation,
        content = content,
    )
}
