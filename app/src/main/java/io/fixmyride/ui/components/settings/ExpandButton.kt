package io.fixmyride.ui.components.settings

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun ExpandButton(
    isExpanded: Boolean,
    onClickExpand: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                color = ColorPalette.secondary,
                shape = RoundedCornerShape(100),
            )
            .clip(RoundedCornerShape(100))
            .clickable { onClickExpand() },
    ) {
        val rotation = animateFloatAsState(
            targetValue = when (isExpanded) {
                true -> 180f
                else -> 0f
            },
            animationSpec = Measurements.scrollAnimation(duration = 200),
            label = "",
        )
        Icon(
            Icons.Rounded.KeyboardArrowDown,
            contentDescription = "Expand option",
            tint = ColorPalette.background,
            modifier = Modifier
                .animateContentSize()
                .rotate(
                    degrees = rotation.value,
                ),
        )
    }
}