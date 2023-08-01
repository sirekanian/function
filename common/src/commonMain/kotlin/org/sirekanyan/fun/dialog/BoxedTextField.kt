package org.sirekanyan.`fun`.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.imePadding

@Composable
fun BoxedTextField(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, readOnly: Boolean) {
    val focusRequester = remember { FocusRequester() }
    Box(Modifier.fillMaxSize().padding(24.dp)) {
        @OptIn(ExperimentalMaterial3Api::class)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .focusRequester(focusRequester),
            placeholder = { Text("Put something here...") },
            readOnly = readOnly,
        )
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
