package io.fixmyride.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun UniversalHeader(caption: String, navCtrl: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            Icons.Rounded.KeyboardArrowLeft,
            contentDescription = "Go back to previous page",
            tint = ColorPalette.background,
            modifier = Modifier
                .background(
                    color = ColorPalette.secondary,
                    shape = RoundedCornerShape(100),
                )
                .clickable { navCtrl.popBackStack() },
        )

        Spacer(Modifier.width(10.dp))

        Text(
            caption,
            style = Typing.screenHeadline,
        )
    }
}