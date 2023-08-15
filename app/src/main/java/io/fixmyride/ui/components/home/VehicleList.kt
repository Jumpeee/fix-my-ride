package io.fixmyride.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun VehicleList() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = ColorPalette.secondary,
                shape = RoundedCornerShape(40f.dp),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 10.dp,
                    horizontal = 25.dp,
                )
        ) {
            Headline()
            for (e in 0..50) {
                VehicleItem()
            }
        }
    }
}

@Composable
private fun Headline() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            stringResource(R.string.all_vehicles),
            style = Typing.categoryHeadline,
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(
                    width = 0.5f.dp,
                    color = ColorPalette.background.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(15f),
                )
                .clip(RoundedCornerShape(15f))
                .clickable { /* TODO */ },
        ) {
            Text(
                stringResource(R.string.sort),
                style = Typing.outlinedButtonText,
                modifier = Modifier.padding(
                    horizontal = 5.dp,
                    vertical = 2.dp,
                )
            )
        }
    }
}