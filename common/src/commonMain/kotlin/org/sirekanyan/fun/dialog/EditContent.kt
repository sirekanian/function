package org.sirekanyan.`fun`.dialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.BackHandler
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.data.FunRepository
import org.sirekanyan.`fun`.imePadding
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.EditScreen
import org.sirekanyan.`fun`.model.HomeScreen

@Composable
fun EditContent(state: AppState, screen: EditScreen, repository: FunRepository) {
    val item = screen.initialItem
    val initialContent = item.content
    val initialDraft = remember {
        val index = initialContent.indexOf('\n').takeIf { it >= 0 } ?: Int.MAX_VALUE
        TextFieldValue(initialContent, TextRange(index))
    }
    var draft by remember { mutableStateOf(initialDraft) }
    val focusManager = LocalFocusManager.current
    fun onBack() {
        if (screen.readOnly) {
            state.screen = HomeScreen
        } else {
            focusManager.clearFocus()
            draft = initialDraft // todo: ask user for confirmation
            screen.readOnly = true
        }
    }
    BackHandler {
        onBack()
    }
    val haptics = LocalHapticFeedback.current
    val borderColor = if (screen.readOnly) Color.Transparent else MaterialTheme.colorScheme.primary
    Box(
        modifier = @OptIn(ExperimentalFoundationApi::class) Modifier
            .fillMaxSize()
            .imePadding()
            .border(1.dp, borderColor, MaterialTheme.shapes.large)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {},
                onLongClick = {
                    screen.readOnly = false
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                },
            ),
    ) {
        BoxedTextField(draft, { draft = it }, readOnly = screen.readOnly, scrollState = screen.toolbar.scrollState)
        SmallToolbar(
            icon = if (screen.readOnly) Icons.Default.ArrowBack else Icons.Default.Close,
            onIconClick = { onBack() },
            title = if (screen.toolbar.isTop) "" else draft.text.substringBefore('\n'),
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
                        enabled = initialContent != draft.text,
                    ) {
                        Text("Save")
                    }
                }
            },
        )
    }
}
