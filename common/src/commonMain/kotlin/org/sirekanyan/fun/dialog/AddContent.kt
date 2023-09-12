package org.sirekanyan.`fun`.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.sirekanyan.`fun`.BackHandler
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.data.FunRepository
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.HomeScreen
import java.util.*

@Composable
fun AddContent(state: AppState, repository: FunRepository) {
    var draft by remember { mutableStateOf(TextFieldValue("")) }
    BackHandler {
        state.screen = HomeScreen
    }
    Box(Modifier.fillMaxSize()) {
        BoxedTextField(value = draft, onValueChange = { draft = it }, readOnly = false)
        SmallToolbar(
            icon = Icons.Default.ArrowBack,
            onIconClick = { state.screen = HomeScreen },
            title = "Add function",
            action = {
                TextButton(
                    onClick = {
                        repository.putContent(UUID.randomUUID().toString(), draft.text)
                        state.screen = HomeScreen
                    },
                    enabled = draft.text.isNotEmpty()
                ) {
                    Text("Save")
                }
            },
        )
    }
}
