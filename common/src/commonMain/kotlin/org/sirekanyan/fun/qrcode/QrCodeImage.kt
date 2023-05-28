package org.sirekanyan.`fun`.qrcode

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import java.util.UUID

@Composable
fun QrCodeImage(text: String, uuid: UUID) {
    val gradient = remember { createQrCodeGradient(uuid) }
    Image(
        bitmap = generateQrCode(text),
        contentDescription = null,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(gradient)
            .padding(16.dp)
            .size(200.dp),
        filterQuality = FilterQuality.None,
    )
}
