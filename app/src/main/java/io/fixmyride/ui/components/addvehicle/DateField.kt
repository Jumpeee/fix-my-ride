package io.fixmyride.ui.components.addvehicle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(caption: String) {
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

            Spacer(Modifier.width(5.dp))

            Box(
                modifier = Modifier.border(
                    color = ColorPalette.secondary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(100),
                    width = 2.dp,
                ),
            ) {
                Text(
                    "i",
                    style = TextStyle(
                        color = ColorPalette.secondary.copy(alpha = 0.2f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    ),
                    modifier = Modifier.padding(
                        horizontal = 7.5.dp,
                        vertical = 1.dp,
                    ),
                )
            }
        }

        Spacer(Modifier.height(5.dp))

        TextField(
            value = "",
            onValueChange = {},
            readOnly = true,
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
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    "Pick a date",
                    style = Typing.textFieldPlaceholder,
                )
            }
        )

    }
}