package io.fixmyride.ui.components.notifications

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun NoNotificationsIndicator() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        CrossedOutIcon()
        Spacer(Modifier.height(10.dp))

        Text(
            stringResource(R.string.you_have_no_notifications),
            style = Typing.emptyScreenText,
        )
    }
}

@Composable
private fun CrossedOutIcon() {
    Box(
        modifier = Modifier
            .size(110.dp)
            .background(
                color = ColorPalette.tertiary,
                shape = RoundedCornerShape(100),
            ),
    ) {
        Icon(
            Icons.Rounded.Notifications,
            contentDescription = "No notifications icon",
            tint = ColorPalette.secondary,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(100)),
        ) {
            val path = Path()
            path.moveTo(0f, 0f)
            path.lineTo(this.size.height, this.size.width)
            drawPath(path, color = ColorPalette.tertiary, style = Stroke(15f))
        }
    }
}
