package io.fixmyride.ui.components.settings

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun Option(icon: ImageVector, name: String, description: String) {
    Column {
        Spacer(Modifier.height(30.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconAndOptionName(icon, name, description)
            SwitchAndExpand {
                // TODO implement expanding
            }

        }
    }
}

@Composable
private fun IconAndOptionName(icon: ImageVector, name: String, description: String) {
    Row {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(
                color = ColorPalette.primary,
                shape = RoundedCornerShape(10.dp)
            ),
        ) {
            Icon(
                icon,
                contentDescription = "Option icon",
                tint = ColorPalette.background,
                modifier = Modifier.padding(8.dp),
            )
        }

        Spacer(Modifier.width(10.dp))

        Column {
            Text(
                name,
                style = Typing.subheading,
            )
            Text(
                description,
                style = Typing.descriptionBody,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}

@Composable
private fun SwitchAndExpand(onClickExpand: () -> Unit) {
    Row {
        // TODO implement turning on/off
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(
                color = ColorPalette.green.copy(alpha = 0.1f),
                shape = RoundedCornerShape(5.dp),
            ),
        ) {
            Text(
                stringResource(R.string.enabled),
                style = Typing.enabled,
                modifier = Modifier
                    .padding(
                        horizontal = 5.dp,
                        vertical = 2.dp,
                    ),
            )
        }

        Spacer(Modifier.width(10.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = ColorPalette.secondary,
                    shape = RoundedCornerShape(100),
                )
                .clickable { onClickExpand() },
        ) {
            Icon(
                Icons.Rounded.KeyboardArrowDown,
                contentDescription = "Expand option",
                tint = ColorPalette.background,
            )
        }
    }
}


