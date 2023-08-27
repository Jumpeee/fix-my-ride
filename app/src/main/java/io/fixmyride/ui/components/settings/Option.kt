package io.fixmyride.ui.components.settings

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun Option(
    icon: ImageVector,
    name: String,
    description: String,
    iconRotate: Float = 0f,
) {
    Column {
        Spacer(Modifier.height(30.dp))

        val isExpanded = remember { mutableStateOf(false) }

        Box {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier.fillMaxSize(),
            ) {
                IconAndOptionName(
                    icon,
                    name,
                    description,
                    iconRotate,
                    isExpanded.value,
                )
            }
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxSize(),
            ) {
                SwitchAndExpand(isExpanded.value) { isExpanded.value = !isExpanded.value }
            }
        }

    }
}

@Composable
internal fun IconAndOptionName(
    icon: ImageVector,
    name: String,
    description: String,
    iconRotate: Float,
    isExpanded: Boolean,
) {
    Row {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(
                color = ColorPalette.primary,
                shape = Measurements.roundedShape,
            ),
        ) {
            Icon(
                icon,
                contentDescription = "Option icon",
                tint = ColorPalette.background,
                modifier = Modifier
                    .padding(8.dp)
                    .rotate(iconRotate),
            )
        }

        Spacer(Modifier.width(10.dp))

        Column(Modifier.animateContentSize()) {
            Text(
                name,
                style = Typing.subheading,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                description,
                style = Typing.descriptionBody,
                overflow = TextOverflow.Ellipsis,
                maxLines = when (isExpanded) {
                    true -> Int.MAX_VALUE
                    else -> 1
                },
            )
        }
    }
}

@Composable
private fun SwitchAndExpand(
    isExpanded: Boolean,
    onClickExpand: () -> Unit,
) {
    // TODO synchronize it with real settings
    val isEnabled = remember { mutableStateOf(false) }
    Row {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = when (isEnabled.value) {
                        true -> ColorPalette.green.copy(alpha = 0.1f)
                        else -> ColorPalette.lightRed.copy(alpha = 0.1f)
                    },
                    shape = RoundedCornerShape(5.dp),
                )
                .animateContentSize()
                .clickable { isEnabled.value = !isEnabled.value },
        ) {
            Text(
                text = when (isEnabled.value) {
                    true -> stringResource(R.string.enabled)
                    else -> stringResource(R.string.disabled)
                },
                style = when (isEnabled.value) {
                    true -> Typing.enabled
                    else -> Typing.disabled
                },
                modifier = Modifier
                    .padding(
                        horizontal = 5.dp,
                        vertical = 2.dp,
                    ),
            )
        }

        Spacer(Modifier.width(10.dp))

        ExpandButton(isExpanded) { onClickExpand() }
    }
}


