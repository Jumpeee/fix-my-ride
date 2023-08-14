package io.fixmyride.ui.components.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fixmyride.ui.theme.Typing
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette

@Composable
fun VehicleList() {
    Column {
        Headline()
        Spacer(Modifier.height(10.dp))

        Box {
            for (i in 0..10) {
                VehicleItem(i)
            }
        }
    }

}

@Composable
private fun Headline() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            stringResource(R.string.all_vehicles),
            style = Typing.screenHeadline,
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(
                    width = 1.3f.dp,
                    color = ColorPalette.secondary.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(20),
                )
                .clickable { /* TODO */ }
        ) {
            Text(
                stringResource(R.string.sort),
                style = Typing.outlinedButtonText,
                modifier = Modifier.padding(
                    horizontal = 7.dp,
                    vertical = 3.dp,
                )
            )
        }
    }
}