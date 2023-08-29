package io.fixmyride.ui.components.addvehicle

import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.unit.times
import androidx.compose.ui.window.Dialog
import io.fixmyride.R
import io.fixmyride.models.DateType
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomDatePicker(onDismiss: (LocalDate?) -> Unit) {
    val isParseError = remember { mutableStateOf(false) }

    var day = ""
    var month = ""
    var year = ""

    Dialog(
        onDismissRequest = { onDismiss(null) },
    ) {
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
                        stringResource(R.string.pick_a_date),
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

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    DateBox(
                        dateType = DateType.YEAR,
                        parseError = isParseError.value,
                    ) {
                        isParseError.value = false
                        year = it
                    }

                    Spacer(Modifier.width(10.dp))

                    DateBox(
                        dateType = DateType.MONTH,
                        parseError = isParseError.value,
                    ) {
                        isParseError.value = false
                        month = it
                    }

                    Spacer(Modifier.width(10.dp))

                    DateBox(
                        dateType = DateType.DAY,
                        parseError = isParseError.value,
                    ) {
                        isParseError.value = false
                        day = it
                    }
                }

                Spacer(Modifier.height(10.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = ColorPalette.primary,
                            shape = Measurements.roundedShape,
                        )
                        .clickable(onClickLabel = "Save selected date") {
                            if (year.isEmpty() || month.isEmpty() || day.isEmpty()) {
                                isParseError.value = true
                                return@clickable
                            }

                            year = convertToValidDate(year, DateType.YEAR, 4)
                            month = convertToValidDate(month, DateType.MONTH, 2)
                            day = convertToValidDate(day, DateType.DAY, 2)

                            val pattern = DateTimeFormatter.ofPattern("yyyy.MM.dd")
                            val parsedDate = LocalDate.parse("$year.$month.$day", pattern)
                            onDismiss(parsedDate)
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

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateBox(
    dateType: DateType,
    parseError: Boolean,
    onInput: (String) -> Unit,
) {
    val placeholderText = when (dateType) {
        DateType.YEAR -> "yyyy"
        DateType.MONTH -> "mm"
        DateType.DAY -> "dd"
    }

    val maxLength = when (dateType) {
        DateType.YEAR -> 4
        DateType.MONTH -> 2
        DateType.DAY -> 2
    }

    val deviceMetrics = Resources.getSystem().displayMetrics

    val dateValue = remember { mutableStateOf("") }
    TextField(
        value = dateValue.value,
        onValueChange = {
            if (it.length > maxLength) return@TextField

            var formattedValue = it.replace(Regex("\\D+"), "")
            if (formattedValue.length == maxLength) {
                formattedValue = convertToValidDate(formattedValue, dateType, maxLength)
            }

            onInput(formattedValue)
            dateValue.value = formattedValue
        },
        textStyle = Typing.textFieldText,
        maxLines = 1,
        shape = Measurements.roundedShape,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
            .width(((deviceMetrics.widthPixels / deviceMetrics.density).dp - (2 * Measurements.screenPadding) - (2 * (Measurements.screenPadding / 2))) / 3 - 20.dp)
            .border(
                color = when (parseError) {
                    true -> ColorPalette.lightRed
                    else -> ColorPalette.secondary.copy(alpha = 0.1f)
                },
                width = 2.dp,
                shape = Measurements.roundedShape,
            ),
    )
}

@RequiresApi(Build.VERSION_CODES.O)
private fun convertToValidDate(
    value: String,
    dateType: DateType,
    maxLength: Int
): String {
    if (value.isEmpty()) return ""

    val now = LocalDate.now()
    val maxNumber = when (dateType) {
        DateType.YEAR -> now.year + 100
        DateType.MONTH -> 12
        DateType.DAY -> 31
    }
    val minNumber = when (dateType) {
        DateType.YEAR -> now.year
        DateType.MONTH -> 1
        DateType.DAY -> 1
    }

    val result = when {
        value.toInt() >= maxNumber -> "$maxNumber"
        value.toInt() <= minNumber -> "$minNumber"
        else -> value
    }

    return result.padStart(maxLength, '0')
}