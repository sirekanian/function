package org.sirekanyan.`fun`.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.sirekanyan.`fun`.BackHandler
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.data.FunRepository
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.EditScreen
import org.sirekanyan.`fun`.model.HomeScreen

@Composable
fun EditContent(state: AppState, screen: EditScreen, repository: FunRepository) {
    val item = screen.initialItem
    var draft by remember { mutableStateOf(TextFieldValue(item.content)) }
    BackHandler {
        state.screen = HomeScreen
    }
    Box(Modifier.fillMaxSize()) {
        BoxedTextField(draft, { draft = it }, readOnly = screen.readOnly, scrollState = screen.toolbar.scrollState)
        SmallToolbar(
            icon = Icons.Default.ArrowBack,
            onIconClick = { state.screen = HomeScreen },
            title = "",
            elevation = screen.toolbar.elevation,
            action = {
                if (screen.readOnly) {
                    IconButton(
                        onClick = {
                            screen.readOnly = false
                        },
                    ) {
                        Icon(Icons.Default.Edit, null)
                    }
                } else if (draft.text.isEmpty()) {
                    TextButton(
                        onClick = {
                            repository.delete(item.id, item.timestamp)
                            state.screen = HomeScreen
                        },
                    ) {
                        Text("Delete", color = MaterialTheme.colorScheme.error)
                    }
                } else {
                    TextButton(
                        onClick = {
                            repository.putContent(item.id, draft.text)
                            state.screen = HomeScreen
                        },
                        enabled = item.content != draft.text
                    ) {
                        Text("Save")
                    }
                }
            },
        )
    }
}
