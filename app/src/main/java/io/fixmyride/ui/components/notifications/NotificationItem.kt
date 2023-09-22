package io.fixmyride.ui.components.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.database.DatabaseManager
import io.fixmyride.models.Notification
import io.fixmyride.models.Vehicle
import io.fixmyride.ui.components.ExpandButton
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationItem(notification: Notification) {
    val vehicle = remember { mutableStateOf(Vehicle.EMPTY) }
    LaunchedEffect(Unit) {
        val db = DatabaseManager.getInstance().dao
        vehicle.value = db.getVehicleById(notification.relatedVehicleId)
    }

    Column {
        Spacer(Modifier.height(30.dp))

        val isExpanded = remember { mutableStateOf(false) }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.background(
                        color = ColorPalette.primary,
                        shape = Measurements.roundedShape,
                    )
                ) {
                    Icon(
                        painterResource(R.drawable.truck_icon),
                        contentDescription = "Truck icon",
                        tint = ColorPalette.background,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp),
                    )
                }
                Spacer(Modifier.width(10.dp))

                Column {
                    Text(
                        "Volvo XC40",
                        style = Typing.subheading,
                    )
                    Text(
                        "XDR 1238",
                        style = Typing.descriptionBody,
                    )
                }
            }

            ExpandButton(isExpanded.value) { isExpanded.value = !isExpanded.value }
        }
    }
}