package io.fixmyride.ui.theme

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.dp

class Measurements {
    companion object {
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
    }
}