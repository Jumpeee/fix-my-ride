package io.fixmyride.ui.components.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.fixmyride.ui.components.ExpandButton
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun Option(
    icon: ImageVector,
    name: String,
    description: String,
    content: @Composable () -> Unit,
) {
    Column {
        Spacer(Modifier.height(30.dp))

        val isExpanded = remember { mutableStateOf(false) }
        Box(contentAlignment = Alignment.TopEnd) {
            IconAndOptionName(
                icon,
                name,
                description,
                0f,
                isExpanded.value,
            )

            ExpandButton(isExpanded.value) {
                isExpanded.value = !isExpanded.value
            }
        }
        AnimatedVisibility(visible = isExpanded.value) {
            content()
        }
    }
}

@Composable
internal fun IconAndOptionName(
    icon: ImageVector,
    name: String,
    description: String,
    rotate: Float,
    isExpanded: Boolean,
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier.fillMaxSize(),
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
                        .rotate(rotate),
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
}


