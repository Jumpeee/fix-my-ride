package io.fixmyride.ui.components.addvehicle

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun DateField(
    caption: String,
    hintHeadline: String,
    hintDescription: String,
) {
    val showInfoDialog = remember { mutableStateOf(false) }
    val showDatePicker = remember { mutableStateOf(false) }

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
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(16.dp)
                    .border(
                        color = ColorPalette.secondary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(100),
                        width = 2.dp,
                    )
                    .clickable { showInfoDialog.value = true },
            ) {
                Text(
                    "i",
                    style = TextStyle(
                        color = ColorPalette.secondary.copy(alpha = 0.2f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                )
            }
        }

        Spacer(Modifier.height(5.dp))

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(Measurements.textFieldHeight)
                .background(
                    color = ColorPalette.tertiary,
                    shape = RoundedCornerShape(10.dp),
                )
                .clickable { showDatePicker.value = true },
        ) {
            Text(
                stringResource(R.string.pick_a_date),
                style = Typing.textFieldPlaceholder,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

    if (showDatePicker.value) {
        CustomDatePicker {
            showDatePicker.value = false
            // TODO
        }
    }

    if (showInfoDialog.value) {
        InfoDialog(
            hintHeadline,
            hintDescription,
        ) { showInfoDialog.value = false }
    }
}

@Composable
fun InfoDialog(
    headline: String,
    description: String,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Surface(
            color = ColorPalette.background,
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
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
                        modifier = Modifier.clickable { onDismiss() }
                    )
                }

                Text(
                    description,
                    style = Typing.descriptionBody,
                )

                Spacer(Modifier.height(10.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = ColorPalette.primary,
                            shape = RoundedCornerShape(5.dp),
                        )
                        .clickable { onDismiss() },
                ) {
                    Text(
                        stringResource(R.string.close),
                        style = Typing.buttonText,
                        modifier = Modifier.padding(vertical = 5.dp),
                    )
                }
            }
        }
    }
}