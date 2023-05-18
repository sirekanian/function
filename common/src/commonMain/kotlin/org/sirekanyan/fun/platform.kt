package org.sirekanyan.`fun`

import androidx.compose.ui.graphics.ImageBitmap

expect val platformName: String

expect fun ImageBitmap.installMonochromePixels(pixels: BooleanArray)
