package io.fixmyride.ui.components.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import kotlinx.coroutines.launch

@Composable
fun NavBar(scrollState: ScrollState, navCtrl: NavController) {
    val coroutineScope = rememberCoroutineScope()

    val offsetY = animateDpAsState(
        targetValue = if (scrollState.value > 200) 0.dp else 150.dp,
        animationSpec = Measurements.scrollAnimation(delay = 125),
        label = "",
    )

    val selectedItem = remember { mutableIntStateOf(0) }
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .offset(y = offsetY.value)
                .shadow(
                    elevation = 7.dp,
                    spotColor = ColorPalette.background,
                    ambientColor = ColorPalette.background,
                    clip = true,
                    shape = RoundedCornerShape(20.dp),
                )
                .background(
                    color = ColorPalette.tertiary,
                    shape = RoundedCornerShape(50),
                ),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                NavItem(
                    index = 0,
                    selectedIndex = selectedItem.intValue,
                    icon = Icons.Rounded.Home,
                ) {
                    selectedItem.intValue = 0
                    coroutineScope.launch {
                        scrollState.animateScrollTo(
                            0,
                            animationSpec = Measurements.scrollAnimation(duration = 1000),
                        )
                    }
                }

                NavItem(
                    index = 1,
                    selectedIndex = selectedItem.intValue,
                    icon = Icons.Rounded.Add,
                ) {
                    navCtrl.navigate("/add-vehicle")
                    selectedItem.intValue = 1
                }

                NavItem(
                    index = 2,
                    selectedIndex = selectedItem.intValue,
                    icon = Icons.Rounded.Notifications,
                ) {
                    navCtrl.navigate("/notifications")
                    selectedItem.intValue = 2
                }
            }
        }
    }
}

@Composable
private fun NavItem(
    index: Int,
    selectedIndex: Int,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    val isSelected = index == selectedIndex

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier.background(
                color = when (isSelected) {
                    true -> ColorPalette.secondary
                    else -> ColorPalette.tertiary
                },
                shape = RoundedCornerShape(100),
            ),
        ) {
            Icon(
                icon,
                contentDescription = "Navigation bar element",
                tint = when (isSelected) {
                    true -> ColorPalette.tertiary
                    else -> ColorPalette.secondary.copy(alpha = 0.75f)
                },
                modifier = Modifier
                    .padding(7.dp)
                    .size(28.dp),
            )
        }
    }
}