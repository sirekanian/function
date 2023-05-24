package org.sirekanyan.`fun`

import android.content.Context
import android.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.sirekanyan.`fun`.data.FunDatabase

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
