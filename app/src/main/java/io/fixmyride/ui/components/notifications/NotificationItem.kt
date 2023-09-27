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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
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
                        text = when {
                            vehicle.value.model.length > 12 -> {
                                vehicle.value.model
                                    .substring(0, 11)
                                    .trim() + "..."
                            }

                            else -> vehicle.value.model
                        },
                        maxLines = 1,
                        style = Typing.subheading,
                    )
                    Text(
                        vehicle.value.registration,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = Typing.descriptionBody,
                    )
                }

            }
            WarningsAndExpand(
                expired = notification.expirations.filter { it < 0 }.size,
                warnings = notification.expirations.filter { it > 0 }.size,
                expanded = isExpanded.value,
            ) {
                isExpanded.value = !isExpanded.value
            }
        }
    }
}

@Composable
fun WarningsAndExpand(
    expired: Int,
    warnings: Int,
    expanded: Boolean,
    onClickExpand: () -> Unit,
) {
    val hasOnlyExpirationsOrWarnings = when {
        expired == 0 && warnings != 0 -> true
        expired != 0 && warnings == 0 -> true
        else -> false
    }

    Row {
        if (expired != 0) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = ColorPalette.lightRed.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(5.dp),
                    )
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Text(
                    text = "$expired ${if (hasOnlyExpirationsOrWarnings) stringResource(R.string.expirations) else ""}".trim(),
                    style = Typing.expiredText,
                    modifier = Modifier
                        .padding(
                            horizontal = 5.dp,
                            vertical = 2.dp,
                        ),
                )
            }
        }

        if (!hasOnlyExpirationsOrWarnings) Spacer(Modifier.width(5.dp))


        if (warnings != 0) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = ColorPalette.yellow.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(5.dp),
                    )
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Text(
                    text = "$warnings ${if (hasOnlyExpirationsOrWarnings) stringResource(R.string.warnings) else ""}".trim(),
                    style = Typing.warningText,
                    modifier = Modifier
                        .padding(
                            horizontal = 5.dp,
                            vertical = 2.dp,
                        ),
                )
            }
        }
        Spacer(Modifier.width(10.dp))

        ExpandButton(expanded) { onClickExpand() }
    }
}