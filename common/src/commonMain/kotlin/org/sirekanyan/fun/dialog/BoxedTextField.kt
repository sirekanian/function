package org.sirekanyan.`fun`.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.imePadding

@Composable
fun BoxedTextField(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, readOnly: Boolean) {
    val focusRequester = remember { FocusRequester() }
    Box(Modifier.fillMaxSize().padding(24.dp)) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .focusRequester(focusRequester),
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.bodyLarge.copy(LocalContentColor.current),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        )
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
