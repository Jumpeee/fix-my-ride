package io.fixmyride.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleValueDialog(
    headline: String,
    placeholderText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onDismiss: (String?) -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss(null) }) {
        Surface(
            shape = Measurements.roundedShape,
            color = ColorPalette.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(Measurements.screenPadding / 2),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        headline,
                        style = Typing.subheading,
                    )
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Close dialog",
                        tint = ColorPalette.lightRed,
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { onDismiss(null) },
                    )
                }
                Spacer(Modifier.height(10.dp))

                val inputValue = remember { mutableStateOf("") }
                TextField(
                    value = inputValue.value,
                    onValueChange = { inputValue.value = it.replace(Regex("\\D+"), "") },
                    textStyle = Typing.textFieldText,
                    maxLines = 1,
                    shape = Measurements.roundedShape,
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = ColorPalette.tertiary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = ColorPalette.primary,
                        selectionColors = TextSelectionColors(
                            backgroundColor = ColorPalette.primary,
                            handleColor = ColorPalette.primary,
                        ),
                    ),
                    placeholder = {
                        Text(
                            placeholderText,
                            style = Typing.textFieldPlaceholder,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    modifier = Modifier
                        .height(Measurements.textFieldHeight)
                        .fillMaxWidth()
                        .border(
                            color = ColorPalette.secondary.copy(alpha = 0.1f),
                            width = 2.dp,
                            shape = Measurements.roundedShape,
                        ),
                )
                Spacer(Modifier.height(10.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = ColorPalette.primary,
                            shape = Measurements.roundedShape,
                        )
                        .clickable(onClickLabel = "Save value") {
                            if (inputValue.value.isBlank()) {
                                onDismiss(null)
                            } else {
                                onDismiss(inputValue.value)
                            }
                        }
                ) {
                    Text(
                        stringResource(R.string.save),
                        style = Typing.buttonText,
                        modifier = Modifier.padding(vertical = 5.dp),
                    )
                }
            }
        }
    }
}