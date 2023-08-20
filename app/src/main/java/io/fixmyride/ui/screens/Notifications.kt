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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.ui.components.FloatingButton
import io.fixmyride.ui.components.ResultsBar
import io.fixmyride.ui.components.UniversalHeader
import io.fixmyride.ui.components.notifications.NotificationItem
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import kotlinx.coroutines.launch

@Composable
fun NotificationsScreen(navCtrl: NavController) {
    Surface(
        color = ColorPalette.background,
        modifier = Modifier.padding(
            top = Measurements.screenPadding,
            start = Measurements.screenPadding,
            end = Measurements.screenPadding,
        ),
    ) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            UniversalHeader(stringResource(R.string.notifications), navCtrl)


            for (i in 0..100) NotificationItem()

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
                    0,
                    animationSpec = Measurements.scrollAnimation(duration = 1000)
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
        ) {
            // TODO deleting all notifications
        }
    }
}