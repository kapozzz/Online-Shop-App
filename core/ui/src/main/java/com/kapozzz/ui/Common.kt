package com.kapozzz.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val background: Color,
    val onBackground: Color,
    val container: Color,
    val onContainer: Color,
    val outline: Color,
    val error: Color,
    val firstTint: Color
)

enum class ColorStyle() {
    Base
}

object AppTheme {
    val colors: Colors
        @Composable
        get() = LocalColors.current

}

internal val LocalColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}
