package io.fixmyride.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.data.models.Vehicle
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing
import io.fixmyride.utils.Routes

@Composable
fun VehicleItem(
    navCtrl: NavController,
    vehicle: Vehicle,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .clickable {
                navCtrl.navigate("${Routes.SELECTED_VEHICLE}/${vehicle.id}")
            },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(
                color = ColorPalette.background,
                shape = RoundedCornerShape(12.dp),
            )
        ) {
            Icon(
                painterResource(R.drawable.truck_icon),
                contentDescription = "Phone icon",
                tint = ColorPalette.secondary,
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp),
            )
        }
        Spacer(Modifier.width(10.dp))

        Column {
            Text(
                vehicle.model,
                style = Typing.bookmarkSubHeadline,
            )
            Text(
                vehicle.registration,
                style = Typing.bookmarkBody,
            )
        }
    }
}
