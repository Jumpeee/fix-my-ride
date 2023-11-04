package io.fixmyride.ui.components.settings

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing


@Composable
fun ValueBox(
    isSelected: Boolean,
    value: String,
    onClick: (Int?) -> Unit,
) {
    val isValueANumber = try {
        Integer.parseInt(value)
        true
    } catch (_: NumberFormatException) {
        false
    }

    val animBackgroundColor = animateColorAsState(
        targetValue = when (isSelected) {
            true -> ColorPalette.primary
            else -> ColorPalette.tertiary
        },
        label = "Animated background color",
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .background(
                color = animBackgroundColor.value,
                shape = RoundedCornerShape(100),
            )
            .clickable {
                try {
                    val parsedVal = Integer.parseInt(value)
                    onClick(parsedVal)
                } catch (_: NumberFormatException) {
                    onClick(null)
                }
            },
    ) {
        Text(
            text = when (isValueANumber) {
                true -> "$value ${stringResource(R.string.days)}"
                false -> value
            },
            style = when (isSelected) {
                true -> Typing.selectedValueBoxText
                false -> Typing.unselectedValueBoxText
            },
            modifier = Modifier.padding(
                horizontal = when (isValueANumber) {
                    true -> 10.dp
                    false -> 20.dp
                },
                vertical = 5.dp,
            )
        )
    }
}