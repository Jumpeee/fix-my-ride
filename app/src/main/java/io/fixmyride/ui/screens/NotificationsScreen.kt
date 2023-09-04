package io.fixmyride.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.database.DatabaseManager
import io.fixmyride.enums.Decision
import io.fixmyride.models.Notification
import io.fixmyride.ui.components.EmptyPageIndicator
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.ResultsBar
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.dialogs.DecisionDialog
import io.fixmyride.ui.components.notifications.NotificationItem
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun NotificationsScreen(
    navCtrl: NavController,
) {
    val notifications = remember { mutableStateOf<List<Notification>>(emptyList()) }
    LaunchedEffect(Unit) {
        val db = DatabaseManager.getInstance().dao
        val coroutine = CoroutineScope(Dispatchers.Default)
        coroutine.launch { notifications.value = db.getNotifications() }
    }

    val scrollState = rememberScrollState()
    val showDeleteNotificationsDialog = remember { mutableStateOf(false) }
    Surface(
        color = ColorPalette.background,
        modifier = Modifier.padding(horizontal = Measurements.screenPadding),
    ) {

        Column(
            modifier = when (notifications.value.size) {
                0 -> Modifier
                else -> Modifier.verticalScroll(scrollState)
            },
        ) {
            Spacer(Modifier.height(Measurements.screenPadding))
            UniversalHeader(stringResource(R.string.notifications), navCtrl)

            when (notifications.value.size) {
                0 -> EmptyPageIndicator(
                    bottomText = {
                        Text(
                            stringResource(R.string.you_have_no_notifications),
                            style = Typing.emptyScreenText,
                        )
                    },
                    backgroundColor = ColorPalette.tertiary,
                    icon = Icons.Rounded.Notifications,
                    iconColor = ColorPalette.secondary,
                )

                else -> for (n in notifications.value) NotificationItem(n)
            }

            Spacer(Modifier.height(100.dp))
        }
    }

    val coroutineScope = rememberCoroutineScope()
    FloatingButton(
        color = ColorPalette.primary,
        icon = Icons.Rounded.KeyboardArrowUp,
        alignment = Alignment.BottomEnd,
        scrollState = scrollState
    ) {
        coroutineScope.launch {
            scrollState.animateScrollTo(
                0, animationSpec = Measurements.scrollAnimation(duration = 1000)
            )
        }
    }

    ResultsBar(
        results = 12,
        alignment = Alignment.BottomCenter,
        animationSpec = Measurements.scrollAnimation(delay = 250),
        scrollState = scrollState,
    )

    FloatingButton(
        color = ColorPalette.lightRed,
        icon = Icons.Rounded.Delete,
        alignment = Alignment.BottomStart,
        animationSpec = Measurements.scrollAnimation(delay = 125),
        scrollState = scrollState,
    ) { showDeleteNotificationsDialog.value = true }

    if (showDeleteNotificationsDialog.value) {
        DecisionDialog(
            headline = stringResource(R.string.delete_notifications_headline),
            description = stringResource(R.string.delete_notifications_description),
        ) {
            if (it == Decision.YES) {
                val db = DatabaseManager.getInstance().dao
                val coroutine = CoroutineScope(Dispatchers.Default)
                coroutine.launch {
                    db.deleteNotifications()
                    notifications.value = emptyList()
                }
            }
            showDeleteNotificationsDialog.value = false
        }
    }
}