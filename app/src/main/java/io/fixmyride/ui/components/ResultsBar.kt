package io.fixmyride.ui.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.fixmyride.R
import io.fixmyride.ui.theme.ColorPalette
import io.fixmyride.ui.theme.Measurements
import io.fixmyride.ui.theme.Typing

@Composable
fun ResultsBar(
    results: Int,
    alignment: Alignment,
    animationSpec: AnimationSpec<Dp> = Measurements.scrollAnimation(),
    scrollState: ScrollState,
) {
    val offsetY = animateDpAsState(
        targetValue = when (scrollState.value > Measurements.scrollPositionToShowToolbar()) {
            true -> 0.dp
            false -> 150.dp
        },
        animationSpec = animationSpec,
        label = "Results bar scroll animation",
    )

    Box(
        contentAlignment = alignment,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = ColorPalette.tertiary,
            ),
            modifier = Modifier
                .offset(y = offsetY.value)
                .clip(RoundedCornerShape(50))
        ) {
            Text(
                "$results ${stringResource(R.string.results)}",
                style = Typing.defaultBody,
                modifier = Modifier.padding(10.dp),
            )
        }
    }
}
