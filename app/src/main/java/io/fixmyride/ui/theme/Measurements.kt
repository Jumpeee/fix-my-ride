package io.fixmyride.ui.theme

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object Measurements {
    val screenPadding = 20.dp
    fun scrollPositionToShowToolbar(value: Int = 200): Int = value
    fun <T> scrollAnimation(
        delay: Int = 0,
        duration: Int = 500,
        easing: Easing = FastOutSlowInEasing,
    ) = tween<T>(
        durationMillis = duration,
        delayMillis = delay,
        easing = easing,
    )

    val textFieldHeight = 50.dp
    val roundedShape = RoundedCornerShape(10.dp)
}