package org.sirekanyan.`fun`.qrcode

import androidx.compose.ui.graphics.ImageBitmap
import com.google.zxing.BarcodeFormat.QR_CODE
import com.google.zxing.EncodeHintType.MARGIN
import com.google.zxing.qrcode.QRCodeWriter
import org.sirekanyan.`fun`.installMonochromePixels

fun generateQrCode(text: String): ImageBitmap {
    val matrix = QRCodeWriter().encode(text, QR_CODE, 0, 0, mapOf(MARGIN to 0))
    val width = matrix.width
    val height = matrix.height
    val pixels = BooleanArray(width * height)
    for (x in 0 until width) {
        for (y in 0 until height) {
            pixels[x + width * y] = matrix.get(x, y)
        }
    }
    return ImageBitmap(width, height).also { bitmap ->
        bitmap.installMonochromePixels(pixels)
    }
}
