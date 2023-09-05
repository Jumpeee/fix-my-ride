package io.fixmyride.ui.components.managevehicle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormField(
    initialValue: String? = null,
    caption: String,
    placeholder: String,
    onInput: (String) -> Unit,
) {
    val fieldValue = remember { mutableStateOf("") }

    if (initialValue != null) {
        LaunchedEffect(Unit) {
            fieldValue.value = initialValue
        }
    }

    Column {

        Spacer(Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Canvas(modifier = Modifier.size(10.dp)) {
                drawCircle(color = ColorPalette.primary)
            }

            Spacer(Modifier.width(5.dp))

            Text(
                caption,
                style = Typing.subheading,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(Modifier.height(5.dp))

        TextField(
            value = fieldValue.value,
            onValueChange = {
                fieldValue.value = it
                onInput(it)
            },
            textStyle = Typing.textFieldText,
            maxLines = 1,
            shape = Measurements.roundedShape,
            placeholder = {
                Text(
                    placeholder,
                    style = Typing.textFieldPlaceholder,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = fieldValue.value.isNotEmpty(),
                    enter = slideInVertically { -30 } + fadeIn(),
                    exit = slideOutVertically { 30 } + fadeOut(),
                ) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Clear text",
                        tint = ColorPalette.lightRed,
                        modifier = Modifier.clickable { fieldValue.value = "" }
                    )
                }
            },
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
            modifier = Modifier
                .fillMaxWidth()
                .height(Measurements.textFieldHeight)
                .border(
                    color = ColorPalette.secondary.copy(alpha = 0.1f),
                    width = 2.dp,
                    shape = Measurements.roundedShape,
                ),
        )
    }
}