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

object Typing {
    /** Appears at the top of each page */
    val screenHeadline = TextStyle(
        color = ColorPalette.secondary,
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    )

    /** e.g. the one used in AddVehicleButton at the top-left corner */
    val categoryHeadline = TextStyle(
        color = ColorPalette.background.copy(alpha = 0.75f),
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 19.sp,
    )

    /** e.g. headline for Add Vehicle Button */
    val bookmarkSubHeadline = TextStyle(
        color = ColorPalette.background,
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 22.sp,
    )

    /** e.g. the one used in AddVehicleButton as description */
    val bookmarkBody = TextStyle(
        color = ColorPalette.background.copy(alpha = 0.6f),
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    /** e.g. button next to "All vehicles" headline on Home Page */
    val outlinedButtonText = TextStyle(
        color = ColorPalette.secondary,
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 16.sp,
    )

    /** e.g. for Results Bar */
    val defaultBody = TextStyle(
        color = ColorPalette.secondary,
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    /** e.g. notification headline */
    val subheading = TextStyle(
        color = ColorPalette.secondary,
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    )

    /** e.g. notification body */
    val descriptionBody = TextStyle(
        color = ColorPalette.secondary.copy(alpha = 0.6f),
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    /** e.g. settings page for every single option */
    val enabled = TextStyle(
        color = ColorPalette.green,
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    /** e.g. settings page for every single option */
    val disabled = TextStyle(
        color = ColorPalette.lightRed,
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    /** e.g. notifications for every single vehicle warning text */
    val warningText = TextStyle(
        color = ColorPalette.yellow,
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    /** e.g. notifications for every single vehicle expiration text */
    val expiredText = TextStyle(
        color = ColorPalette.lightRed,
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    /** e.g. notifications for every single vehicle active text */
    val activeText = TextStyle(
        color = ColorPalette.green,
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    /** e.g. notifications for every single vehicle not set text */
    val notSetText = TextStyle(
        color = ColorPalette.secondary.copy(alpha = 0.5f),
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

    /** Used only for text field texts */
    val textFieldText = TextStyle(
        color = ColorPalette.secondary,
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    )

    /** Used only for text field placeholders */
    val textFieldPlaceholder = TextStyle(
        color = ColorPalette.secondary.copy(alpha = 0.5f),
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    )

    /** e.g. close button for use in dialogs */
    val buttonText = TextStyle(
        color = ColorPalette.background,
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    )

    /** e.g. in Notifications screen when user has no notifications under the crossed out notification icon */
    val emptyScreenText = TextStyle(
        color = ColorPalette.secondary,
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )

    /** Used for value boxes in settings */
    val selectedValueBoxText = TextStyle(
        color = ColorPalette.background,
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 22.sp,
    )

    /** Used for value boxes in settings */
    val unselectedValueBoxText = TextStyle(
        color = ColorPalette.secondary,
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 22.sp,
    )
}