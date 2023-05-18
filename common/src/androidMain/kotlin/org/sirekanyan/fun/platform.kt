package org.sirekanyan.`fun`

import android.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap

actual val platformName: String = "Android"

actual fun ImageBitmap.installMonochromePixels(pixels: BooleanArray) {
    val colors = IntArray(pixels.size) { index ->
        if (pixels[index]) Color.BLACK else Color.WHITE
    }
    asAndroidBitmap().setPixels(colors, 0, width, 0, 0, width, height)
}
