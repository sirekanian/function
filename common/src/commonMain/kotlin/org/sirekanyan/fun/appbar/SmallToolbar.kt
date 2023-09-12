package org.sirekanyan.`fun`.appbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.D
import org.sirekanyan.`fun`.statusBarsPadding

@Composable
fun SmallToolbar(
    icon: ImageVector,
    onIconClick: () -> Unit,
    title: String,
    action: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
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
            modifier = Modifier.fillMaxHeight().paddingFromBaseline(bottom = 24.dp),
        )
        action?.let {
            Spacer(Modifier.weight(1f))
            action()
            Spacer(Modifier.width(24.dp))
        }
    }
}
