package org.sirekanyan.`fun`

import android.content.Context
import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.sirekanyan.`fun`.data.FunDatabase
import androidx.activity.compose.BackHandler as AndroidBackHandler

actual val platformName: String = "Android"

actual fun ImageBitmap.installMonochromePixels(pixels: BooleanArray) {
    val colors = IntArray(pixels.size) { index ->
        if (pixels[index]) Color.BLACK else Color.TRANSPARENT
    }
    asAndroidBitmap().setPixels(colors, 0, width, 0, 0, width, height)
}

lateinit var androidApplicationContext: Context

actual fun createSqlDriver(): SqlDriver =
    AndroidSqliteDriver(FunDatabase.Schema, androidApplicationContext, "fun.db")

@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
    AndroidBackHandler(enabled, onBack)
}
