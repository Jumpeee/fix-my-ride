package io.fixmyride.ui.components.notifications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun AuthorInfo() {
    val uriHandler = LocalUriHandler.current
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            stringResource(R.string.made_by),
            style = Typing.descriptionBody,
        )
        Text(" Jumpee ",
            style = Typing.descriptionBody.copy(color = ColorPalette.primary),
            modifier = Modifier.clickable { uriHandler.openUri("https://github.com/Jumpeee") })
        Icon(
            Icons.Rounded.Favorite,
            contentDescription = "Heart",
            tint = ColorPalette.lightRed,
            modifier = Modifier.size(14.dp),
        )
    }
}