package org.sirekanyan.`fun`

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.EditScreen
import org.sirekanyan.`fun`.model.Item

@Composable
fun HomeContent(paddings: PaddingValues, state: AppState, items: List<Item>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .systemBarsPadding()
            .padding(paddings)
            .padding(vertical = 16.dp),
    ) {
        items.forEach { item ->
            Row(
                modifier = @OptIn(ExperimentalFoundationApi::class) Modifier
                    .fillMaxWidth()
                    .combinedClickable(onClick = {}, onLongClick = {
                        state.screen = EditScreen(item)
                    })
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(item.content)
                Text(
                    text = item.id.takeLast(6),
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }
    }
}
