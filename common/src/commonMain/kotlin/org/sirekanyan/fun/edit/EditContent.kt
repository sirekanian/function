package org.sirekanyan.`fun`.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.BackHandler
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.data.FunRepository
import org.sirekanyan.`fun`.imePadding
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.MainScreen
import org.sirekanyan.`fun`.systemBarsPadding

@Composable
fun EditContent(state: AppState, repository: FunRepository) {
    var draft by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    BackHandler {
        state.screen = MainScreen
    }
    Column(Modifier.fillMaxSize().systemBarsPadding()) {
        SmallToolbar(
            icon = Icons.Default.ArrowBack,
            onIconClick = { state.screen = MainScreen },
            title = "Add function",
            action = {
                TextButton(
                    onClick = {
                        repository.putContent(draft)
                        state.screen = MainScreen
                    },
                    enabled = draft.isNotEmpty()
                ) {
                    Text("Save")
                }
            },
        )
        Box(Modifier.fillMaxSize().padding(24.dp)) {
            @OptIn(ExperimentalMaterial3Api::class)
            OutlinedTextField(
                value = draft,
                onValueChange = { draft = it },
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .focusRequester(focusRequester),
                placeholder = { Text("Put something here...") },
            )
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
