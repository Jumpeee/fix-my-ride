package io.fixmyride.ui.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun AddVehicleButton(navCtrl: NavController) {
    val backgroundHeight = 120.dp
    val primaryBoxHeightPercent = 0.7f
    val topOffset = (backgroundHeight - backgroundHeight * primaryBoxHeightPercent).value

    Box(
        modifier = Modifier.clickable { navCtrl.navigate("/add-vehicle-screen") },
    ) {
        Background(backgroundHeight, primaryBoxHeightPercent)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = (topOffset / 2 - 16 / 2).dp,
                    horizontal = 25.dp,
                )
        ) {
            Headline()
            ArrowIcon()
            TruckIcon()
            Description()
        }
    }
}


@Composable
private fun Background(height: Dp, primaryBoxHeightPercent: Float) {
    val cornerRadius = 80f

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(30)),
    ) {
        drawRoundRect(
            color = ColorPalette.tertiary,
            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
        )

        val topOffset = this.size.height - this.size.height * primaryBoxHeightPercent
        drawRoundRect(
            color = ColorPalette.primary,
            size = Size(this.size.width, this.size.height * primaryBoxHeightPercent),
            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
            topLeft = Offset(0f, topOffset)
        )

        val curveStart = this.size.width / 3
        drawRect(
            color = ColorPalette.primary,
            size = Size(curveStart, this.size.height)
        )

        val curvePath = Path()
        curvePath.moveTo(this.size.width / 3, 0f)
        curvePath.cubicTo(
            this.size.width / 2.2f, 0f,
            this.size.width / 2.1f, topOffset,
            this.size.width / 1.6f, topOffset,
        )
        curvePath.lineTo(curveStart, topOffset)
        curvePath.close()

        drawPath(
            path = curvePath,
            color = ColorPalette.primary,
        )

    }
}

@Composable
private fun ArrowIcon() {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            Icons.Rounded.KeyboardArrowRight,
            contentDescription = "Go to add vehicle page",
            tint = ColorPalette.background,
            modifier = Modifier
                .background(
                    color = ColorPalette.secondary,
                    shape = RoundedCornerShape(100),
                )
                .size(16.dp),
        )
    }
}

@Composable
private fun Headline() {
    Box(
        contentAlignment = Alignment.TopStart,
    ) {
        Text(
            stringResource(R.string.add_vehicle),
            style = Typing.bookmarkHeadline,
        )
    }
}

@Composable
private fun TruckIcon() {
    // TODO
}

@Composable
private fun Description() {
    // TODO
}
