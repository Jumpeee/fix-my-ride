package io.fixmyride.ui.components.addvehicle

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateField(
    caption: String,
    hintHeadline: String,
    hintDescription: String,
) {
    val showInfoDialog = remember { mutableStateOf(false) }
    val showDatePicker = remember { mutableStateOf(false) }

    val dateValue = remember { mutableStateOf<String?>(null) }
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

            Icon(
                Icons.Outlined.Info,
                contentDescription = "Info",
                tint = ColorPalette.secondary.copy(alpha = 0.2f),
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(Modifier.height(5.dp))

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(Measurements.textFieldHeight)
                .background(
                    color = ColorPalette.tertiary,
                    shape = Measurements.roundedShape,
                )
                .clickable { showDatePicker.value = true },
        ) {
            if (dateValue.value == null) {
                Text(
                    text = stringResource(R.string.pick_a_date),
                    style = Typing.textFieldPlaceholder,
                    modifier = Modifier.padding(start = 16.dp)
                )
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = dateValue.value!!,
                        style = Typing.textFieldText,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = stringResource(R.string.year_month_day),
                        style = Typing.textFieldPlaceholder,
                    )
                }
            }
        }
    }

    if (showDatePicker.value) {
        CustomDatePicker {
            showDatePicker.value = false
            if (it == null) return@CustomDatePicker
            dateValue.value = it.toString().replace("-", ".")
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
private fun InfoDialog(
    headline: String,
    description: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            color = ColorPalette.background,
            shape = Measurements.roundedShape,
        ) {
            Column(modifier = Modifier.padding(Measurements.screenPadding / 2)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
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
                            .clickable { onDismiss() }
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