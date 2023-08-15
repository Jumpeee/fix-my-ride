package io.fixmyride.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import io.fixmyride.ui.theme.ColorPalette

@Composable
fun ScrollToTopButton() {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = ColorPalette.primary,
            ),
            modifier = Modifier
                .shadow(
                    elevation = 7.dp,
                    spotColor = ColorPalette.background,
                    ambientColor = ColorPalette.background,
                    clip = true,
                    shape = RoundedCornerShape(20.dp),
                )
                .clip(RoundedCornerShape(20.dp))
                .clickable { /* TODO */ },
        ) {
            Icon(
                Icons.Rounded.KeyboardArrowUp,
                contentDescription = "Scroll to top",
                tint = ColorPalette.background,
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp),
            )
        }
    }
}