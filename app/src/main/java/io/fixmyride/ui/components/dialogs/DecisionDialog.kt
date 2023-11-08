package io.fixmyride.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import io.fixmyride.R
import io.fixmyride.data.enums.Decision
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun DecisionDialog(
    headline: String,
    description: String,
    onDismiss: (Decision?) -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss(null) }) {
        Surface(
            color = ColorPalette.background,
            shape = Measurements.roundedShape,
        ) {
            Column(modifier = Modifier.padding(Measurements.screenPadding / 2)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        headline,
                        style = Typing.subheading,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Close dialog",
                        tint = ColorPalette.lightRed,
                        modifier = Modifier
                            .size(18.dp)
                            .clip(RoundedCornerShape(100))
                            .clickable { onDismiss(null) })
                }
                Text(
                    description,
                    style = Typing.descriptionBody,
                )

                Spacer(Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    DialogDecisionButton(Decision.YES) { onDismiss(Decision.YES) }
                    DialogDecisionButton(Decision.NO) { onDismiss(Decision.NO) }
                }
            }
        }
    }
}

@Composable
private fun DialogDecisionButton(
    decision: Decision,
    onClick: () -> Unit,
) {
    val backgroundColor = when (decision) {
        Decision.YES -> ColorPalette.green.copy(alpha = 0.1f)
        Decision.NO -> ColorPalette.lightRed.copy(alpha = 0.1f)
    }

    val buttonText = when (decision) {
        Decision.YES -> stringResource(R.string.yes)
        Decision.NO -> stringResource(R.string.no)
    }

    val textStyle = when (decision) {
        Decision.YES -> Typing.enabled
        Decision.NO -> Typing.disabled
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(Measurements.roundedShape)
            .background(
                color = backgroundColor,
                shape = Measurements.roundedShape,
            )
            .clickable { onClick() },
    ) {
        Text(
            buttonText,
            style = textStyle.copy(fontSize = 16.sp),
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 5.dp,
            ),
        )
    }
}