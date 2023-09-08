package io.fixmyride.ui.components

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.fixmyride.R
import io.fixmyride.enums.ManageVehicleType
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing
import io.fixmyride.utils.ImageUtils
import java.io.File

@Composable
fun VehicleThumbnail(
    viewType: ManageVehicleType,
    imagePath: String?,
    allowEditing: Boolean = false,
    onSelect: (String?) -> Unit,
) {
    val image = remember {
        mutableStateOf(
            when (imagePath) {
                null -> null
                else -> Uri.fromFile(File(imagePath))
            }
        )
    }
    val ctx = LocalContext.current
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        image.value = it
        onSelect(ImageUtils.getRealPathFromUri(ctx, it))
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
                        cornerRadius = CornerRadius(40f, 40f),
                        style = Stroke(
                            width = 6f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
                        ),
                    )
                }
                .clip(Measurements.roundedShape)
                .clickable {
                    if (allowEditing) {
                        galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                }

            else -> Modifier.wrapContentSize()
        },
    ) {
        ThumbnailPicture(image.value, viewType)
        if (allowEditing) {
            EditIcon(image.value != null, galleryLauncher)
        }
    }
}

@Composable
private fun ThumbnailPicture(
    imagePath: Uri?,
    viewType: ManageVehicleType
) {
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
private fun EditIcon(
    visible: Boolean,
    galleryLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
) {
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
                    .clickable {
                        galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
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


