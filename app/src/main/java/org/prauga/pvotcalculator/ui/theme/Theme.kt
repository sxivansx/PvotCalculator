package org.prauga.pvotcalculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val CalculatorColorScheme = darkColorScheme(
    primary = CalcOrange,
    onPrimary = CalcWhite,
    background = CalcBlack,
    onBackground = CalcWhite,
    surface = CalcDarkGray,
    onSurface = CalcWhite,
    secondaryContainer = CalcLightGray,
    onSecondaryContainer = CalcBlack,
    surfaceVariant = CalcBlack,
    onSurfaceVariant = CalcLightGray,
)

@Composable
fun CalculatorTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CalculatorColorScheme,
        typography = Typography,
        content = content
    )
}
