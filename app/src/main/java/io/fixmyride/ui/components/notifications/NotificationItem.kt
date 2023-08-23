package io.fixmyride.ui.components.notifications

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Typing

@Composable
fun NotificationItem(/* TODO notification: Notification */) {
    val isExpanded = remember { mutableStateOf(false) }

    Column {
        Spacer(Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .clickable { isExpanded.value = !isExpanded.value }
        ) {
            Row {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.background(
                        color = ColorPalette.primary,
                        shape = RoundedCornerShape(100),
                    )
                ) {
                    Icon(
                        Icons.Rounded.Email,
                        contentDescription = "Notification",
                        tint = ColorPalette.background,
                        modifier = Modifier
                            .padding(3.dp)
                            .size(18.dp),
                    )
                }

                Spacer(Modifier.width(10.dp))

                Column {
                    Text(
                        // TODO change the text
                        stringResource(R.string.tpl_is_about_to_expire),
                        style = Typing.subheading,
                    )

                    Text(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                        style = Typing.descriptionBody,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = when (isExpanded.value) {
                            true -> Int.MAX_VALUE
                            else -> 2
                        }
                    )

                    Spacer(Modifier.height(10.dp))

                    // TODO get date from notification
                    Text(
                        "08-19-2023",
                        style = Typing.descriptionBody,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}