package io.fixmyride.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun EmptyPageIndicator(
    bottomText: @Composable () -> Unit,
    backgroundColor: Color,
    backgroundSize: Dp = 110.dp,
    icon: Any,
    iconColor: Color,
    iconPadding: Dp = 10.dp,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        CrossedOutIcon(
            icon,
            iconColor,
            iconPadding,
            backgroundColor,
            backgroundSize,
        )
        Spacer(Modifier.height(10.dp))

        bottomText()
    }
}

@Composable
private fun CrossedOutIcon(
    icon: Any,
    iconColor: Color,
    iconPadding: Dp,
    backgroundColor: Color,
    backgroundSize: Dp,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(backgroundSize)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(100),
            ),
    ) {
        when (icon) {
            is ImageVector -> Icon(
                icon,
                contentDescription = "Empty page icon",
                tint = iconColor,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(iconPadding),
            )

            is Painter -> Icon(
                icon,
                contentDescription = "Empty page icon",
                tint = iconColor,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(iconPadding),
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(100)),
        ) {
            val path = Path()
            path.moveTo(0f, 0f)
            path.lineTo(this.size.height, this.size.width)
            drawPath(path, color = backgroundColor, style = Stroke(15f))
        }
    }
}