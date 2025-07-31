package dev.greenox.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.greenox.R

// Set of Material typography styles to start with

val Poppins = FontFamily(
    Font(resId = R.font.poppins_thin, weight = FontWeight.Thin),
    Font(resId = R.font.poppins_extra_light, weight = FontWeight.ExtraLight),
    Font(resId = R.font.poppins_light, weight = FontWeight.Light),
    Font(resId = R.font.poppins_regular),
    Font(resId = R.font.poppins_medium, weight = FontWeight.Medium),
    Font(resId = R.font.poppins_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.poppins_bold, weight = FontWeight.Bold),
    Font(resId = R.font.poppins_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.poppins_black, weight = FontWeight.Black),
    Font(resId = R.font.poppins_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
    Font(resId = R.font.poppins_extra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(resId = R.font.poppins_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.poppins_italic, style = FontStyle.Italic),
    Font(resId = R.font.poppins_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.poppins_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(resId = R.font.poppins_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.poppins_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(resId = R.font.poppins_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
)

val Typography = Typography(
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
)