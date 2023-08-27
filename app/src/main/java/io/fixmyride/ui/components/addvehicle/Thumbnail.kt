package io.fixmyride.ui.components.addvehicle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements

@Composable
fun Thumbnail() {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.wrapContentSize(),
    ) {
        ThumbnailPicture()
        EditIcon()
    }
}

@Composable
private fun ThumbnailPicture() {
    Box(
        modifier = Modifier
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 15.dp,
            )
            .background(
                color = ColorPalette.tertiary,
                shape = Measurements.roundedShape,
            ),
    ) {
        // TODO implement image
        Image(
            painter = painterResource(R.drawable.thumbnail),
            contentDescription = "Vehicle image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
        )
    }
}

@Composable
private fun EditIcon() {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = ColorPalette.primary,
                    shape = RoundedCornerShape(100),
                )
                .clickable { /* TODO */ },
        ) {
            Icon(
                Icons.Outlined.Edit,
                contentDescription = "Pick an image icon",
                tint = ColorPalette.background,
                modifier = Modifier
                    .padding(5.dp)
                    .size(20.dp),
            )
        }
    }
}