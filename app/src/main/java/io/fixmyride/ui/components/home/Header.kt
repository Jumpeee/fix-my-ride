package io.fixmyride.ui.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun Header(navCtrl: NavController) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                stringResource(R.string.home_page),
                style = Typing.screenHeadline,
            )
            Row {
                Icon(
                    Icons.Outlined.Settings,
                    contentDescription = "Go to settings",
                    tint = ColorPalette.secondary,
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .clickable { navCtrl.navigate("/settings") }
                )

                Spacer(Modifier.width(15.dp))

                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = "Go to notifications page",
                    tint = ColorPalette.secondary,
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .clickable { navCtrl.navigate("/notifications") }
                )
            }

        }

    }
}