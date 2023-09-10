package io.fixmyride.ui.components.settings

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun OptionButton(
    icon: ImageVector,
    name: String,
    buttonText: String,
    description: String,
    iconRotate: Float = 0f,
    onButtonClick: () -> Unit,
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
                ButtonAndExpand(
                    buttonText,
                    isExpanded.value,
                    onButtonClick,
                ) { isExpanded.value = !isExpanded.value }
            }
        }
    }
}

@Composable
private fun ButtonAndExpand(
    buttonText: String,
    isExpanded: Boolean,
    onButtonClick: () -> Unit,
    onClickExpand: () -> Unit,
) {
    val borderRadius = RoundedCornerShape(5.dp)
    Row {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = ColorPalette.blue.copy(alpha = 0.1f),
                    shape = borderRadius,
                )
                .clip(borderRadius)
                .clickable { onButtonClick() }
        ) {
            Text(
                text = buttonText,
                style = Typing.optionButton,
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

