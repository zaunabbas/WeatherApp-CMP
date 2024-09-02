package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkColor,
    primaryVariant = Purple700,
    onPrimary = DarkColor,
    secondary = Red300,
    error = Red200,
    onSecondary = Color.White
)

private val LightColorPalette = lightColors(
    primary = LightColor,
    primaryVariant = Purple700,
    onPrimary = LightColor,
    secondary = Teal200,
    error = Red800,
    onSecondary = Color.Black
)

@Composable
fun WeatherAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}