package org.sirekanyan.`fun`

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.sirekanyan.`fun`.data.FunDatabase

actual fun ImageBitmap.installMonochromePixels(pixels: BooleanArray) {
    val bytes = ByteArray(pixels.size * 4) { index ->
        if (pixels[index / 4] && index % 4 > 2) -1 else 0
    }
    asSkiaBitmap().installPixels(bytes)
}

actual fun createSqlDriver(): SqlDriver =
    JdbcSqliteDriver("jdbc:sqlite:fun.db").also { driver ->
        FunDatabase.Schema.create(driver)
    }

@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
    // not applicable for this platform
}

@Composable
actual fun ScanButton() {
    // not applicable for this platform
}

actual fun Modifier.imePadding(): Modifier =
    this // not applicable for this platform

actual fun Modifier.systemBarsPadding(): Modifier =
    this // not applicable for this platform

actual fun Modifier.statusBarsPadding(): Modifier =
    this // not applicable for this platform

actual fun Modifier.navigationBarsPadding(): Modifier =
    this // not applicable for this platform

actual fun isFunctionFlavor(): Boolean =
    false
