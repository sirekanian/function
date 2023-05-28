package org.sirekanyan.`fun`.sync

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.qrcode.createQrCodeGradient
import org.sirekanyan.`fun`.ui.icons.DefaultQrCodeIcon
import java.util.UUID

private val easeInOutSine = CubicBezierEasing(0.37f, 0f, 0.63f, 1f)
private val alphaAnimationSpec: InfiniteRepeatableSpec<Float> =
    infiniteRepeatable(tween(500, 500, easeInOutSine), RepeatMode.Reverse)

@Composable
fun PeerIcon(uuid: UUID, modifier: Modifier) {
    val gradient = remember { createQrCodeGradient(uuid) }
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(1f, 0.5f, alphaAnimationSpec)
    Row(modifier.alpha(alpha), Arrangement.spacedBy(16.dp), Alignment.CenterVertically) {
        Icon(
            imageVector = DefaultQrCodeIcon,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(gradient)
                .padding(4.dp),
            tint = Color.Black,
        )
        Text(
            text = uuid.toString().takeLast(6),
            color = MaterialTheme.colorScheme.primary,
            fontFamily = FontFamily.Monospace,
        )
    }
}
