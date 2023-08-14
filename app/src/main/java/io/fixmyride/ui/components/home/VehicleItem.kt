package io.fixmyride.ui.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import io.fixmyride.ui.theme.ColorPalette

@Composable
fun VehicleItem(index: Int) {
    Box(Modifier.padding(top = index * 60.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) {
            Background(index)
            Content()
        }
    }
}

@Composable
private fun Background(index: Int) {
    Canvas(
        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(15))
    ) {
        val topOffset = this.size.height - this.size.height * 0.7f
        drawRect(
            color = ColorPalette.vehicleCardColors[index % ColorPalette.vehicleCardColors.size],
            size = Size(this.size.width, this.size.height * 0.7f),
            topLeft = Offset(0f, topOffset)
        )
    }
}

@Composable
private fun Content() {
    // TODO
}