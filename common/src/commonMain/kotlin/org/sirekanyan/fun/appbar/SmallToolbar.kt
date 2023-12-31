package org.sirekanyan.`fun`.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.D
import org.sirekanyan.`fun`.statusBarsPadding

@Composable
fun SmallToolbar(
    icon: ImageVector,
    onIconClick: () -> Unit,
    title: String,
    elevation: Dp,
    action: @Composable (() -> Unit)? = null,
) {
    val toolbarColor = MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
    Row(
        modifier = Modifier
            .background(toolbarColor.copy(alpha = 0.98f))
            .fillMaxWidth()
            .statusBarsPadding()
            .height(D.smallToolbarSize),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { onIconClick() },
            modifier = Modifier.size(D.smallToolbarIconSize),
            content = { Icon(icon, null) },
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .fillMaxHeight()
                .paddingFromBaseline(bottom = 24.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        action?.let {
            action()
            Spacer(Modifier.width(24.dp))
        }
    }
}
