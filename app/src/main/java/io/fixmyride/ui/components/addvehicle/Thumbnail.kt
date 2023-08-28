package io.fixmyride.ui.components.addvehicle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun Thumbnail() {
    val showImagePickerSheet = remember { mutableStateOf(false) }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.wrapContentSize(),
    ) {
        ThumbnailPicture()
        EditIcon { showImagePickerSheet.value = true }
    }

    if (showImagePickerSheet.value) {
        ImagePickerDialog { showImagePickerSheet.value = false }
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
private fun EditIcon(onClick: () -> Unit) {
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
                .clickable { onClick() },
        ) {
            Icon(
                Icons.Outlined.Edit,
                contentDescription = "Pick an image button",
                tint = ColorPalette.background,
                modifier = Modifier
                    .padding(5.dp)
                    .size(20.dp),
            )
        }
    }
}

@Composable
private fun ImagePickerDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            color = ColorPalette.background,
            shape = Measurements.roundedShape,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(Measurements.screenPadding / 2),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        stringResource(R.string.choose_the_option),
                        style = Typing.subheading,
                    )
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Close dialog",
                        tint = ColorPalette.lightRed,
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { onDismiss() }
                    )
                }
                Spacer(Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    ImagePickerDialogButton("Take a photo") {}
                    ImagePickerDialogButton("Choose from gallery") {}
                }
            }
        }
    }
}

@Composable
private fun ImagePickerDialogButton(caption: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .background(
                color = ColorPalette.secondary,
                shape = Measurements.roundedShape,
            )
            .clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
        ) {
            Text(
                caption,
                style = Typing.outlinedButtonText.copy(
                    color = ColorPalette.background,
                    fontSize = 12.sp,
                ),
            )

            Spacer(Modifier.width(5.dp))

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .background(
                        color = ColorPalette.background,
                        shape = RoundedCornerShape(100),
                    ),
            ) {
                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "",
                    tint = ColorPalette.secondary,
                    modifier = Modifier.size(18.dp),
                )
            }
        }
    }
}