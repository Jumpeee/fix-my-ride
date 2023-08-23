package io.fixmyride.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.R
import io.fixmyride.ui.theme.Typing

@Composable
fun VehicleItem(navCtrl: NavController /* TODO vehicle: Vehicle */) {
    Box(Modifier.padding(top = 20.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Info()

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(
                    color = ColorPalette.background,
                    shape = RoundedCornerShape(100),
                )
            ) {
                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "View selected vehicle's details",
                    tint = ColorPalette.secondary,
                    modifier = Modifier.clickable { navCtrl.navigate("/selected-vehicle") }
                )
            }
        }
    }
}

@Composable
private fun Info() {
    Row {
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
                "Volvo XC40",
                style = Typing.bookmarkSubHeadline,
            )
            Text(
                "XSD 421578",
                style = Typing.bookmarkBody,
            )
        }
    }
}
