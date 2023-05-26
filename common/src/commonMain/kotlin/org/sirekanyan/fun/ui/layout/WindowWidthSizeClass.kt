package org.sirekanyan.`fun`.ui.layout

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@JvmInline
value class WindowWidthSizeClass private constructor(private val value: Int) {
    companion object {
        val Compact = WindowWidthSizeClass(0)
        val Medium = WindowWidthSizeClass(1)
        val Expanded = WindowWidthSizeClass(2)
        fun fromWidth(width: Dp): WindowWidthSizeClass =
            when {
                width < 0.dp -> error("Width must not be negative")
                width < 600.dp -> Compact
                width < 840.dp -> Medium
                else -> Expanded
            }
    }
}