package io.fixmyride.ui.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun FloatingButton(
    color: Color,
    icon: ImageVector,
    alignment: Alignment,
    animationSpec: AnimationSpec<Dp> = Measurements.scrollAnimation(),
    scrollState: ScrollState? = null,
    onClick: () -> Unit,
) {
    val showElement = when (scrollState) {
        null -> true
        else -> scrollState.value > Measurements.scrollPositionToShowToolbar()
    }

    Box(
        contentAlignment = alignment,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
    ) {
        val offsetY = animateDpAsState(
            targetValue = when (showElement) {
                true -> 0.dp
                else -> 150.dp
            },
            animationSpec = animationSpec,
            label = "Scroll animation",
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = color),
            modifier = Modifier
                .offset(y = offsetY.value)
                .shadow(
                    elevation = 7.dp,
                    spotColor = ColorPalette.background,
                    ambientColor = ColorPalette.background,
                    clip = true,
                    shape = RoundedCornerShape(20.dp),
                )
                .clip(RoundedCornerShape(20.dp))
                .clickable { onClick() },
        ) {
            Icon(
                icon,
                contentDescription = "Scroll to top",
                tint = ColorPalette.background,
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp),
            )
        }
    }
}