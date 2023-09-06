package io.fixmyride.ui.components.viewvehicle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.components.dialogs.InfoDialog
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun DataDisplayField(
    caption: String,
    value: String,
    infoHeadline: String = "",
    infoDescription: String = "",
    isDate: Boolean = false,
) {
    val showInfoDialog = remember { mutableStateOf(false) }

    Column {
        DataDisplayHeadline(caption, isDate) { showInfoDialog.value = true }
        Spacer(Modifier.height(5.dp))

        DataDisplayFrame(value, isDate)
        Spacer(Modifier.size(20.dp))
    }

    if (showInfoDialog.value) {
        InfoDialog(
            headline = infoHeadline,
            description = infoDescription,
        ) { showInfoDialog.value = false }
    }
}

@Composable
private fun DataDisplayHeadline(caption: String, isDate: Boolean, onInfoClick: () -> Unit) {
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
        // TODO display info header
        Spacer(Modifier.width(5.dp))
        if (isDate) {
            Icon(
                Icons.Outlined.Info,
                contentDescription = "Info",
                tint = ColorPalette.secondary.copy(alpha = 0.2f),
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onInfoClick() },
            )
        }
    }
}

@Composable
private fun DataDisplayFrame(value: String, isDate: Boolean) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(Measurements.textFieldHeight)
            .background(
                color = ColorPalette.tertiary,
                shape = Measurements.roundedShape,
            )
            .border(
                color = ColorPalette.secondary.copy(alpha = 0.1f),
                width = 2.dp,
                shape = Measurements.roundedShape,
            ),
    ) {
        Row {
            Text(
                value,
                style = Typing.textFieldText,
                modifier = Modifier.padding(start = 16.dp),
            )
            if (isDate && value != stringResource(R.string.empty)) {
                Spacer(Modifier.width(5.dp))
                Text(
                    text = stringResource(R.string.year_month_day),
                    style = Typing.textFieldPlaceholder,
                )
            }
        }
    }
}