package org.sirekanyan.`fun`.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import org.sirekanyan.`fun`.BackHandler
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.data.FunRepository
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.EditScreen
import org.sirekanyan.`fun`.model.HomeScreen
import org.sirekanyan.`fun`.systemBarsPadding

@Composable
fun EditContent(state: AppState, screen: EditScreen, repository: FunRepository) {
    val item = screen.initialItem
    var draft by remember { mutableStateOf(TextFieldValue(item.content, TextRange(Int.MAX_VALUE))) }
    BackHandler {
        state.screen = HomeScreen
    }
    Column(Modifier.fillMaxSize().systemBarsPadding()) {
        SmallToolbar(
            icon = Icons.Default.ArrowBack,
            onIconClick = { state.screen = HomeScreen },
            title = "Edit function",
            action = {
                if (draft.text.isEmpty()) {
                    TextButton(
                        onClick = {
                            repository.delete(item.id)
                            state.screen = HomeScreen
                        },
                    ) {
                        Text("Delete", color = MaterialTheme.colorScheme.error)
                    }
                } else {
                    TextButton(
                        onClick = {
                            repository.updateContent(item.id, draft.text)
                            state.screen = HomeScreen
                        },
                        enabled = item.content != draft.text
                    ) {
                        Text("Save")
                    }
                }
            },
        )
        BoxedTextField(value = draft, onValueChange = { draft = it })
    }
}
