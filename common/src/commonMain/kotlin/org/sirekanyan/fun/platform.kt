package org.sirekanyan.`fun`

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import app.cash.sqldelight.db.SqlDriver

expect fun ImageBitmap.installMonochromePixels(pixels: BooleanArray)

expect fun createSqlDriver(): SqlDriver

@Composable
expect fun BackHandler(enabled: Boolean = true, onBack: () -> Unit)

@Composable
expect fun ScanButton()

expect fun Modifier.imePadding(): Modifier

expect fun Modifier.systemBarsPadding(): Modifier

expect fun Modifier.statusBarsPadding(): Modifier

expect fun Modifier.navigationBarsPadding(): Modifier

expect fun isFunctionFlavor(): Boolean
