package org.sirekanyan.`fun`

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap

actual val platformName: String = "Desktop"

actual fun ImageBitmap.installMonochromePixels(pixels: BooleanArray) {
    val bytes = ByteArray(pixels.size * 4) { index ->
        if (pixels[index / 4] && index % 4 < 3) 0 else -1
    }
    asSkiaBitmap().installPixels(bytes)
}
