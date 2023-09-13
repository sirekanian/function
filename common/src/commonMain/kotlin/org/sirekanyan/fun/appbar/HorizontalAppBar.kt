package org.sirekanyan.`fun`.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.D
import org.sirekanyan.`fun`.navigationBarsPadding

@Composable
fun HorizontalAppBar(
    actions: @Composable RowScope.() -> Unit,
    fab: @Composable () -> Unit,
) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp).copy(alpha = 0.98f))
                .navigationBarsPadding()
                .fillMaxWidth()
                .height(D.appBarSize)
                .padding(horizontal = D.appBarPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            actions()
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier.padding(end = D.appBarFabPadding),
                contentAlignment = Alignment.Center,
            ) {
                fab()
        }
    }
}
