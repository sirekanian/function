package org.sirekanyan.`fun`

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.sirekanyan.`fun`.data.FunDatabase

actual val platformName: String = "Desktop"

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

actual fun Modifier.navigationBarsPadding(): Modifier =
    this // not applicable for this platform
