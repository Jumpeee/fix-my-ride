package io.fixmyride.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.fixmyride.R


private val customFont = FontFamily(
    Font(
        resId = R.font.inter_regular,
        weight = FontWeight.Normal,
    ),
    Font(
        resId = R.font.inter_thin,
        weight = FontWeight.Thin,
    ),
    Font(
        resId = R.font.inter_bold,
        weight = FontWeight.Bold,
    ),
)

class Typing {
    companion object {
        /** Appears at the top of each page */
        val screenHeadline = TextStyle(
            color = ColorPalette.secondary,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 28.sp,
        )

        /** Eg. appears under headline on home page */
        val screenHeadlineDesc = TextStyle(
            color = ColorPalette.secondary.copy(alpha = .5f),
            fontFamily = customFont,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 22.4.sp,
        )
        /** Eg. the one used in AddVehicleButton at the top-left corner*/
        val bookmarkHeadline = TextStyle(
            color = ColorPalette.background,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 22.4.sp,
        )
    }
}