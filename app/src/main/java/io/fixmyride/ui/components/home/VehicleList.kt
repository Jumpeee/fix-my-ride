package io.fixmyride.ui.components.home

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import io.fixmyride.R
import io.fixmyride.enums.SortType
import io.fixmyride.models.Vehicle
import io.fixmyride.ui.components.EmptyPageIndicator
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun VehicleList(
    navCtrl: NavController,
    vehicles: List<Vehicle>,
    onSortSelect: (SortType) -> Unit,
) {
    if (vehicles.isNotEmpty()) {
        val showSortDialog = remember { mutableStateOf(false) }
        val topPadding = 36.dp
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = ColorPalette.tertiary,
                    shape = RoundedCornerShape(40.dp),
                ),
        ) {
            Background()
            Headline(topPadding) { showSortDialog.value = true }

            Box(Modifier.padding(top = topPadding)) {
                Box(
                    modifier = Modifier
                        .background(
                            color = ColorPalette.secondary,
                            shape = RoundedCornerShape(
                                topEnd = 40.dp,
                                bottomStart = 40.dp,
                                bottomEnd = 40.dp,
                            ),
                        ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = 10.dp,
                                start = 30.dp,
                                end = 30.dp
                            )
                    ) {
                        vehicles.forEach { VehicleItem(navCtrl, it) }
                        Spacer(Modifier.height(10.dp))
                    }

                }
            }
        }

        if (showSortDialog.value) {
            SortDialog {
                if (it != null) {
                    onSortSelect(it)
                }
                showSortDialog.value = false
            }
        }

    } else {
        EmptyPageIndicator(
            bottomText = {
                Text(
                    stringResource(R.string.no_vehicles_found),
                    style = Typing.emptyScreenText,
                )
            },
            backgroundColor = ColorPalette.tertiary,
            icon = painterResource(R.drawable.truck_icon),
            iconColor = ColorPalette.secondary,
            iconPadding = 20.dp,
        )
    }
}

@Composable
private fun Background() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(topStart = 40.dp)),
    ) {
        val curveStart = this.size.width / 2.28f
        val topOffset = this.size.height - this.size.height * 0.64f
        drawRect(
            color = ColorPalette.secondary,
            size = Size(curveStart, topOffset),
        )

        val curvePath = Path()
        curvePath.moveTo(curveStart, 0f)
        curvePath.cubicTo(
            this.size.width / 1.8f, 0f,
            this.size.width / 1.7f, topOffset,
            this.size.width / 1.3f, topOffset,
        )
        curvePath.lineTo(curveStart, topOffset)
        curvePath.close()

        drawPath(
            path = curvePath,
            color = ColorPalette.secondary,
        )
    }
}

@Composable
private fun Headline(
    topPadding: Dp,
    onClickSortButton: () -> Unit,
) {
    val headlineStyle = Typing.categoryHeadline

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = topPadding / 2 - (headlineStyle.lineHeight / 2).value.dp,
            ),
    ) {
        Text(
            stringResource(R.string.all_vehicles),
            style = headlineStyle,
            overflow = TextOverflow.Ellipsis,
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(
                    width = 0.5f.dp,
                    color = ColorPalette.secondary.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(5.dp),
                )
                .clip(RoundedCornerShape(5.dp))
                .clickable { onClickSortButton() },
        ) {
            Text(
                stringResource(R.string.sort),
                style = Typing.outlinedButtonText,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    horizontal = 5.dp,
                    vertical = 2.dp,
                ),
            )
        }
    }
}

@Composable
private fun SortDialog(selectedOption: (SortType?) -> Unit) {
    Dialog(onDismissRequest = { selectedOption(null) }) {
        Surface(
            shape = Measurements.roundedShape,
            color = ColorPalette.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(Measurements.screenPadding / 2),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        stringResource(R.string.sort_vehicles),
                        style = Typing.subheading,
                    )
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Close dialog",
                        tint = ColorPalette.lightRed,
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { selectedOption(null) },
                    )
                }
                Spacer(Modifier.height(10.dp))

                enumValues<SortType>().forEach { st ->
                    SortDialogOption(st) { selectedOption(st) }
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
private fun SortDialogOption(
    sortType: SortType,
    onClick: (SortType) -> Unit,
) {
    val caption = when (sortType) {
        SortType.MODEL -> stringResource(R.string.by_model)
        SortType.REGISTRATION -> stringResource(R.string.by_registration)
        SortType.TPL_INSURANCE -> stringResource(R.string.by_tpl_insurance)
        SortType.COLLISION_INSURANCE -> stringResource(R.string.by_collision_insurance)
        SortType.NEXT_INSPECTION_DATE -> stringResource(R.string.by_inspection_date)
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .background(
                color = ColorPalette.tertiary,
                shape = Measurements.roundedShape,
            )
            .border(
                color = ColorPalette.secondary.copy(alpha = 0.1f),
                width = 2.dp,
                shape = Measurements.roundedShape,
            )
            .clip(Measurements.roundedShape)
            .clickable { onClick(sortType) },

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 10.dp,
                    vertical = 7.5.dp,
                ),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(
                    color = ColorPalette.secondary,
                    shape = RoundedCornerShape(100),
                )
            ) {
                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Sort by selected element",
                    tint = ColorPalette.background,
                    modifier = Modifier.size(18.dp),
                )
            }
            Spacer(Modifier.width(7.5.dp))

            Text(
                caption,
                style = Typing.buttonText.copy(
                    color = ColorPalette.secondary,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}