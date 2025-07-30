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

val Cabin = FontFamily(
    Font(resId = R.font.cabin_regular),
    Font(resId = R.font.cabin_medium, weight = FontWeight.Medium),
    Font(resId = R.font.cabin_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.cabin_bold, weight = FontWeight.Bold),
    Font(resId = R.font.cabin_italic, style = FontStyle.Italic),
    Font(resId = R.font.cabin_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.cabin_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(resId = R.font.cabin_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
)