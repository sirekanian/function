package org.sirekanyan.`fun`.dialog

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.D
import org.sirekanyan.`fun`.imePadding
import org.sirekanyan.`fun`.systemBarsPadding

@Composable
fun BoxedTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    readOnly: Boolean,
    scrollState: ScrollState,
) {
    val titleStyle = MaterialTheme.typography.displaySmall.copy(LocalContentColor.current)
    val bodyStyle = MaterialTheme.typography.bodyLarge.copy(LocalContentColor.current)
    val focusRequester = remember { FocusRequester() }
    Box(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding()
            .padding(top = D.smallToolbarSize)
            .padding(24.dp),
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            enabled = !readOnly,
            readOnly = readOnly,
            textStyle = if ('\n' in value.text) bodyStyle else titleStyle, // workaround to fix cursor size
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            visualTransformation = { input ->
                TransformedText(
                    text = buildAnnotatedString {
                        if ('\n' in input.text) {
                            withStyle(titleStyle.toParagraphStyle()) {
                                withStyle(titleStyle.toSpanStyle()) {
                                    appendLine(input.text.substringBefore('\n'))
                                }
                            }
                            withStyle(bodyStyle.toParagraphStyle()) {
                                withStyle(bodyStyle.toSpanStyle()) {
                                    append(input.text.substringAfter('\n'))
                                }
                            }
                        } else {
                            withStyle(titleStyle.toParagraphStyle()) {
                                withStyle(titleStyle.toSpanStyle()) {
                                    append(input.text)
                                }
                            }
                        }
                    },
                    offsetMapping = OffsetMapping.Identity,
                )
            }
        )
    }
    LaunchedEffect(readOnly) {
        if (!readOnly) {
            focusRequester.requestFocus()
        }
    }
}
