package org.sirekanyan.`fun`

import androidx.compose.ui.graphics.ImageBitmap
import com.squareup.sqldelight.db.SqlDriver

expect val platformName: String

expect fun ImageBitmap.installMonochromePixels(pixels: BooleanArray)

expect fun createSqlDriver(): SqlDriver
