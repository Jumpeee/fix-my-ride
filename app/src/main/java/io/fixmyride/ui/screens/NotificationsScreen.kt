package io.fixmyride.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.components.EmptyPageIndicator
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.ResultsBar
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.notifications.NotificationItem
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing
import io.fixmyride.ui.viewmodels.NotificationsViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationsScreen(viewModel: NotificationsViewModel) {
    LaunchedEffect(Unit) { viewModel.loadNotifications() }

    val scrollState = rememberScrollState()
    Surface(
        color = ColorPalette.background,
        modifier = Modifier.padding(horizontal = Measurements.screenPadding),
    ) {
        Column(
            modifier = when (viewModel.notifications.value.size) {
                0 -> Modifier
                else -> Modifier.verticalScroll(scrollState)
            },
        ) {
            Spacer(Modifier.height(Measurements.screenPadding))
            UniversalHeader(stringResource(R.string.notifications), viewModel.navController)

            when (viewModel.notifications.value.size) {
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

                else -> {
                    for (n in viewModel.notifications.value) {
                        if (n.expirations.isNotEmpty()) {
                            NotificationItem(n)
                        }
                    }
                }
            }

            Spacer(Modifier.height(100.dp))
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
                    value = 0,
                    animationSpec = Measurements.scrollAnimation(duration = 1000),
                )
            }
        }

        ResultsBar(
            results = viewModel.notifications.value.size,
            alignment = Alignment.BottomStart,
            animationSpec = Measurements.scrollAnimation(delay = 250),
            scrollState = scrollState,
        )
    }

}