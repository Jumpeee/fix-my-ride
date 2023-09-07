package io.fixmyride.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import io.fixmyride.R
import io.fixmyride.enums.ManageVehicleType
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun VehicleThumbnail(
    viewType: ManageVehicleType,
    imagePath: String?,
    allowEditing: Boolean = false
) {
    val showImagePickerSheet = remember { mutableStateOf(false) }

    val image = remember {
        mutableStateOf(
            when (imagePath) {
                null -> null
                else -> Uri.parse(imagePath)
            }
        )
    }

    Box(
        contentAlignment = when (image.value) {
            null -> Alignment.Center
            else -> Alignment.BottomEnd
        },
        modifier = when (image.value) {
            null -> Modifier
                .fillMaxWidth()
                .height(100.dp)
                .drawBehind {
                    drawRoundRect(
                        color = ColorPalette.secondary.copy(alpha = 0.3f),
                        cornerRadius = CornerRadius(50f, 50f),
                        style = Stroke(
                            width = 6f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
                        ),
                    )
                }
                .clickable { showImagePickerSheet.value = true }


            else -> Modifier.wrapContentSize()
        },
    ) {
        ThumbnailPicture(image.value, viewType)
        if (allowEditing) {
            EditIcon(image.value != null) { showImagePickerSheet.value = true }
        }
    }

    if (showImagePickerSheet.value) {
        ImagePickerDialog {
            image.value = it
            showImagePickerSheet.value = false
        }
    }
}

@Composable
private fun ThumbnailPicture(imagePath: Uri?, viewType: ManageVehicleType) {
    if (imagePath != null) {
        Box(
            modifier = Modifier
                .background(
                    color = ColorPalette.tertiary,
                    shape = Measurements.roundedShape,
                ),
        ) {
            AsyncImage(
                model = imagePath,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
            )
        }
    } else {
        Column {
            Text(
                text = when (viewType) {
                    ManageVehicleType.PREVIEW -> stringResource(R.string.no_image)
                    else -> stringResource(R.string.select_image)
                },
                style = Typing.emptyThumbnailText,
            )
        }
    }
}

@Composable
private fun EditIcon(visible: Boolean, onClick: () -> Unit) {
    if (visible) {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .padding(
                    bottom = 5.dp,
                    end = 5.dp,
                )
                .fillMaxSize(),
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = ColorPalette.primary,
                ),
                modifier = Modifier
                    .padding(3.dp)
                    .shadow(
                        elevation = 8.dp,
                        ambientColor = ColorPalette.background,
                        spotColor = ColorPalette.background,
                    )
                    .clip(RoundedCornerShape(100))
                    .clickable { onClick() },
            ) {
                Icon(
                    Icons.Outlined.Edit,
                    contentDescription = "Pick an image button",
                    tint = ColorPalette.background,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(14.dp),
                )
            }
        }
    }
}

@Composable
private fun ImagePickerDialog(onDismiss: (Uri?) -> Unit) {
    Dialog(onDismissRequest = { onDismiss(null) }) {
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
                            .clickable { onDismiss(null) }
                    )
                }
                Spacer(Modifier.height(10.dp))

                Column {
                    ImagePickerDialogButton(stringResource(R.string.take_a_photo)) {
                        // TODO
                    }
                    Spacer(Modifier.height(10.dp))

                    val galleryLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.PickVisualMedia(),
                        onResult = { onDismiss(it) }
                    )
                    ImagePickerDialogButton(stringResource(R.string.choose_from_gallery)) {
                        galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
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
            .fillMaxWidth()
            .background(
                color = ColorPalette.tertiary,
                shape = Measurements.roundedShape,
            )
            .border(
                color = ColorPalette.secondary.copy(alpha = 0.1f),
                width = 2.dp,
                shape = Measurements.roundedShape,
            )
            .clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
        ) {

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .background(
                        color = ColorPalette.primary,
                        shape = RoundedCornerShape(100),
                    ),
            ) {
                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "",
                    tint = ColorPalette.background,
                    modifier = Modifier.size(18.dp),
                )
            }

            Spacer(Modifier.width(5.dp))

            Text(
                caption,
                style = Typing.outlinedButtonText.copy(
                    color = ColorPalette.secondary,
                    fontSize = 14.sp,
                ),
            )
        }
    }
}