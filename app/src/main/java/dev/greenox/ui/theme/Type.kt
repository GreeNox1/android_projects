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

val NewsReader = FontFamily(
    Font(resId = R.font.newsreader_9pt_extra_light, weight = FontWeight.ExtraLight),
    Font(resId = R.font.newsreader_14pt_extra_light, weight = FontWeight.ExtraLight),
    Font(resId = R.font.newsreader_24pt_extra_light, weight = FontWeight.ExtraLight),
    Font(resId = R.font.newsreader_36pt_extra_light, weight = FontWeight.ExtraLight),
    Font(resId = R.font.newsreader_60pt_extra_light, weight = FontWeight.ExtraLight),
    Font(resId = R.font.newsreader_9pt_light, weight = FontWeight.Light),
    Font(resId = R.font.newsreader_14pt_light, weight = FontWeight.Light),
    Font(resId = R.font.newsreader_24pt_light, weight = FontWeight.Light),
    Font(resId = R.font.newsreader_36pt_light, weight = FontWeight.Light),
    Font(resId = R.font.newsreader_60pt_light, weight = FontWeight.Light),
    Font(resId = R.font.newsreader_9pt_regular),
    Font(resId = R.font.newsreader_14pt_regular),
    Font(resId = R.font.newsreader_24pt_regular),
    Font(resId = R.font.newsreader_36pt_regular),
    Font(resId = R.font.newsreader_60pt_regular),
    Font(resId = R.font.newsreader_9pt_medium, weight = FontWeight.Medium),
    Font(resId = R.font.newsreader_14pt_medium, weight = FontWeight.Medium),
    Font(resId = R.font.newsreader_24pt_medium, weight = FontWeight.Medium),
    Font(resId = R.font.newsreader_36pt_medium, weight = FontWeight.Medium),
    Font(resId = R.font.newsreader_60pt_medium, weight = FontWeight.Medium),
    Font(resId = R.font.newsreader_9pt_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.newsreader_14pt_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.newsreader_24pt_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.newsreader_36pt_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.newsreader_60pt_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.newsreader_9pt_bold, weight = FontWeight.Bold),
    Font(resId = R.font.newsreader_14pt_bold, weight = FontWeight.Bold),
    Font(resId = R.font.newsreader_24pt_bold, weight = FontWeight.Bold),
    Font(resId = R.font.newsreader_36pt_bold, weight = FontWeight.Bold),
    Font(resId = R.font.newsreader_60pt_bold, weight = FontWeight.Bold),
    Font(resId = R.font.newsreader_9pt_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.newsreader_14pt_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.newsreader_24pt_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.newsreader_36pt_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.newsreader_60pt_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.newsreader_9pt_extra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_14pt_extra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_24pt_extra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_36pt_extra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_60pt_extra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_9pt_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_14pt_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_24pt_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_36pt_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_60pt_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_9pt_italic, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_14pt_italic, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_24pt_italic, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_36pt_italic, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_60pt_italic, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_9pt_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_14pt_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_24pt_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_36pt_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_60pt_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_9pt_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_14pt_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_24pt_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_36pt_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_60pt_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_9pt_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_14pt_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_24pt_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_36pt_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_60pt_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_9pt_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_14pt_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_24pt_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_36pt_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(resId = R.font.newsreader_60pt_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displaySmall = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = NewsReader,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp
    )
)