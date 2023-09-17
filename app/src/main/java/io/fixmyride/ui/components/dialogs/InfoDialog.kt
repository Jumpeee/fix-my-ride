package io.fixmyride.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun InfoDialog(
    headline: String,
    description: String,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            color = ColorPalette.background,
            shape = Measurements.roundedShape,
        ) {
            Column(modifier = Modifier.padding(Measurements.screenPadding / 2)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        headline,
                        style = Typing.subheading,
                    )
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Close dialog",
                        tint = ColorPalette.lightRed,
                        modifier = Modifier
                            .size(18.dp)
                            .clip(RoundedCornerShape(100))
                            .clickable { onDismiss() }
                    )
                }

                Text(
                    description,
                    style = Typing.descriptionBody,
                )

                Spacer(Modifier.height(10.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = ColorPalette.primary,
                            shape = RoundedCornerShape(5.dp),
                        )
                        .clickable { onDismiss() },
                ) {
                    Text(
                        stringResource(R.string.close),
                        style = Typing.buttonText,
                        modifier = Modifier.padding(vertical = 5.dp),
                    )
                }
            }
        }
    }
}