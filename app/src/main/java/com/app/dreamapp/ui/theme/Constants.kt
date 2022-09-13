package com.app.dreamapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

object DreamAppTheme {
    val colors: DreamAppColors
        @Composable
        get() = LocalDreamAppColors.current

    val typography: DreamAppTypography
        @Composable
        get() = LocalDreamAppTypography.current

    val shapes: DreamAppShape
        @Composable
        get() = LocalDreamAppShape.current
}

data class DreamAppColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color
)

data class DreamAppTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
    val caption: TextStyle
)

data class DreamAppShape(
    val padding: Dp,
)

enum class DreamAppColorStyle {
    Purple, Orange, Blue, Red, Green
}

enum class DreamAppTextSize {
    Small, Medium, Big
}

val LocalDreamAppColors = staticCompositionLocalOf<DreamAppColors> {
    error("No colors provided")
}

val LocalDreamAppTypography = staticCompositionLocalOf<DreamAppTypography> {
    error("No font provided")
}

val LocalDreamAppShape = staticCompositionLocalOf<DreamAppShape> {
    error("No font provided")
}

data class SettingsBundle(
    val isDarkMode: Boolean,
    val textSize: DreamAppTextSize,
    val paddingSize: DreamAppTextSize,
    val colorStyle: DreamAppColorStyle
)
