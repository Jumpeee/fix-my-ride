package io.fixmyride.ui.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
                stringResource(R.string.your_vehicles),
                style = Typing.screenHeadline,
            )
            Icon(
                Icons.Outlined.Notifications,
                contentDescription = "Go to notifications page",
                tint = ColorPalette.secondary,
                modifier = Modifier.clickable { navCtrl.navigate("/notifications") }
            )

        }
        Text(
            stringResource(R.string.welcome),
            style = Typing.screenHeadlineDesc,
        )
    }
}