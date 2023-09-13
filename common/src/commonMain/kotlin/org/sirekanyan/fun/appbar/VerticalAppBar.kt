package org.sirekanyan.`fun`.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.D
import org.sirekanyan.`fun`.systemBarsPadding

@Composable
fun VerticalAppBar(
    actions: @Composable ColumnScope.() -> Unit,
    fab: @Composable () -> Unit,
) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
                .fillMaxHeight()
                .width(D.appBarSize)
                .systemBarsPadding()
                .padding(vertical = D.appBarPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.padding(top = D.appBarFabPadding),
                contentAlignment = Alignment.Center,
            ) {
                fab()
            }
            Spacer(Modifier.weight(1f))
            actions()
    }
}
