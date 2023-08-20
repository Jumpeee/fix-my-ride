package io.fixmyride.ui.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun VehicleList() {
    val topPadding = 36.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = ColorPalette.tertiary,
                shape = RoundedCornerShape(40.dp),
            ),
    ) {
        Background()
        Headline(topPadding)

        Box(Modifier.padding(top = topPadding)) {
            Box(
                modifier = Modifier
                    .background(
                        color = ColorPalette.secondary,
                        shape = RoundedCornerShape(
                            topEnd = 40.dp,
                            bottomStart = 40.dp,
                            bottomEnd = 40.dp,
                        ),
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = 10.dp,
                            start = 30.dp,
                            end = 30.dp
                        )
                ) {
                    for (e in 0..50) {
                        VehicleItem()
                    }
                }
            }
        }
    }
}

@Composable
private fun Background() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(topStart = 40.dp)),
    ) {

        val curveStart = this.size.width / 2.28f
        drawRect(
            color = ColorPalette.secondary,
            size = Size(curveStart, this.size.height)
        )

        val topOffset = this.size.height - this.size.height * 0.7f
        drawRect(
            color = ColorPalette.secondary,
            size = Size(curveStart, this.size.height)
        )

        val curvePath = Path()
        curvePath.moveTo(curveStart, 0f)
        curvePath.cubicTo(
            this.size.width / 1.8f, 0f,
            this.size.width / 1.7f, topOffset,
            this.size.width / 1.3f, topOffset,
        )
        curvePath.lineTo(curveStart, topOffset)
        curvePath.close()

        drawPath(
            path = curvePath,
            color = ColorPalette.secondary,
        )
    }
}

@Composable
private fun Headline(topPadding: Dp) {
    val headlineStyle = Typing.categoryHeadline

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = topPadding / 2 - (headlineStyle.lineHeight / 2).value.dp,
            ),
    ) {
        Text(
            stringResource(R.string.all_vehicles),
            style = headlineStyle,
            overflow = TextOverflow.Ellipsis,
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(
                    width = 0.5f.dp,
                    color = ColorPalette.secondary.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(5.dp),
                )
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                    // TODO sort button
                },
        ) {
            Text(
                stringResource(R.string.sort),
                style = Typing.outlinedButtonText,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    horizontal = 5.dp,
                    vertical = 2.dp,
                ),
            )
        }
    }
}