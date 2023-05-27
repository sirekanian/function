package org.sirekanyan.`fun`

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import com.squareup.sqldelight.db.SqlDriver

expect val platformName: String

expect fun ImageBitmap.installMonochromePixels(pixels: BooleanArray)

expect fun createSqlDriver(): SqlDriver

@Composable
expect fun BackHandler(enabled: Boolean = true, onBack: () -> Unit)

expect fun Modifier.imePadding(): Modifier

expect fun Modifier.systemBarsPadding(): Modifier

expect fun Modifier.navigationBarsPadding(): Modifier
