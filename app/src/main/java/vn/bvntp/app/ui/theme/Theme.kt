package com.example.ntp_app.ui.theme
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.ntp_app.backgroundDark
import com.example.ntp_app.backgroundDarkHighContrast
import com.example.ntp_app.backgroundDarkMediumContrast
import com.example.ntp_app.backgroundLight
import com.example.ntp_app.backgroundLightHighContrast
import com.example.ntp_app.backgroundLightMediumContrast
import com.example.ntp_app.errorContainerDark
import com.example.ntp_app.errorContainerDarkHighContrast
import com.example.ntp_app.errorContainerDarkMediumContrast
import com.example.ntp_app.errorContainerLight
import com.example.ntp_app.errorContainerLightHighContrast
import com.example.ntp_app.errorContainerLightMediumContrast
import com.example.ntp_app.errorDark
import com.example.ntp_app.errorDarkHighContrast
import com.example.ntp_app.errorDarkMediumContrast
import com.example.ntp_app.errorLight
import com.example.ntp_app.errorLightHighContrast
import com.example.ntp_app.errorLightMediumContrast
import com.example.ntp_app.inverseOnSurfaceDark
import com.example.ntp_app.inverseOnSurfaceDarkHighContrast
import com.example.ntp_app.inverseOnSurfaceDarkMediumContrast
import com.example.ntp_app.inverseOnSurfaceLight
import com.example.ntp_app.inverseOnSurfaceLightHighContrast
import com.example.ntp_app.inverseOnSurfaceLightMediumContrast
import com.example.ntp_app.inversePrimaryDark
import com.example.ntp_app.inversePrimaryDarkHighContrast
import com.example.ntp_app.inversePrimaryDarkMediumContrast
import com.example.ntp_app.inversePrimaryLight
import com.example.ntp_app.inversePrimaryLightHighContrast
import com.example.ntp_app.inversePrimaryLightMediumContrast
import com.example.ntp_app.inverseSurfaceDark
import com.example.ntp_app.inverseSurfaceDarkHighContrast
import com.example.ntp_app.inverseSurfaceDarkMediumContrast
import com.example.ntp_app.inverseSurfaceLight
import com.example.ntp_app.inverseSurfaceLightHighContrast
import com.example.ntp_app.inverseSurfaceLightMediumContrast
import com.example.ntp_app.onBackgroundDark
import com.example.ntp_app.onBackgroundDarkHighContrast
import com.example.ntp_app.onBackgroundDarkMediumContrast
import com.example.ntp_app.onBackgroundLight
import com.example.ntp_app.onBackgroundLightHighContrast
import com.example.ntp_app.onBackgroundLightMediumContrast
import com.example.ntp_app.onErrorContainerDark
import com.example.ntp_app.onErrorContainerDarkHighContrast
import com.example.ntp_app.onErrorContainerDarkMediumContrast
import com.example.ntp_app.onErrorContainerLight
import com.example.ntp_app.onErrorContainerLightHighContrast
import com.example.ntp_app.onErrorContainerLightMediumContrast
import com.example.ntp_app.onErrorDark
import com.example.ntp_app.onErrorDarkHighContrast
import com.example.ntp_app.onErrorDarkMediumContrast
import com.example.ntp_app.onErrorLight
import com.example.ntp_app.onErrorLightHighContrast
import com.example.ntp_app.onErrorLightMediumContrast
import com.example.ntp_app.onPrimaryContainerDark
import com.example.ntp_app.onPrimaryContainerDarkHighContrast
import com.example.ntp_app.onPrimaryContainerDarkMediumContrast
import com.example.ntp_app.onPrimaryContainerLight
import com.example.ntp_app.onPrimaryContainerLightHighContrast
import com.example.ntp_app.onPrimaryContainerLightMediumContrast
import com.example.ntp_app.onPrimaryDark
import com.example.ntp_app.onPrimaryDarkHighContrast
import com.example.ntp_app.onPrimaryDarkMediumContrast
import com.example.ntp_app.onPrimaryLight
import com.example.ntp_app.onPrimaryLightHighContrast
import com.example.ntp_app.onPrimaryLightMediumContrast
import com.example.ntp_app.onSecondaryContainerDark
import com.example.ntp_app.onSecondaryContainerDarkHighContrast
import com.example.ntp_app.onSecondaryContainerDarkMediumContrast
import com.example.ntp_app.onSecondaryContainerLight
import com.example.ntp_app.onSecondaryContainerLightHighContrast
import com.example.ntp_app.onSecondaryContainerLightMediumContrast
import com.example.ntp_app.onSecondaryDark
import com.example.ntp_app.onSecondaryDarkHighContrast
import com.example.ntp_app.onSecondaryDarkMediumContrast
import com.example.ntp_app.onSecondaryLight
import com.example.ntp_app.onSecondaryLightHighContrast
import com.example.ntp_app.onSecondaryLightMediumContrast
import com.example.ntp_app.onSurfaceDark
import com.example.ntp_app.onSurfaceDarkHighContrast
import com.example.ntp_app.onSurfaceDarkMediumContrast
import com.example.ntp_app.onSurfaceLight
import com.example.ntp_app.onSurfaceLightHighContrast
import com.example.ntp_app.onSurfaceLightMediumContrast
import com.example.ntp_app.onSurfaceVariantDark
import com.example.ntp_app.onSurfaceVariantDarkHighContrast
import com.example.ntp_app.onSurfaceVariantDarkMediumContrast
import com.example.ntp_app.onSurfaceVariantLight
import com.example.ntp_app.onSurfaceVariantLightHighContrast
import com.example.ntp_app.onSurfaceVariantLightMediumContrast
import com.example.ntp_app.onTertiaryContainerDark
import com.example.ntp_app.onTertiaryContainerDarkHighContrast
import com.example.ntp_app.onTertiaryContainerDarkMediumContrast
import com.example.ntp_app.onTertiaryContainerLight
import com.example.ntp_app.onTertiaryContainerLightHighContrast
import com.example.ntp_app.onTertiaryContainerLightMediumContrast
import com.example.ntp_app.onTertiaryDark
import com.example.ntp_app.onTertiaryDarkHighContrast
import com.example.ntp_app.onTertiaryDarkMediumContrast
import com.example.ntp_app.onTertiaryLight
import com.example.ntp_app.onTertiaryLightHighContrast
import com.example.ntp_app.onTertiaryLightMediumContrast
import com.example.ntp_app.outlineDark
import com.example.ntp_app.outlineDarkHighContrast
import com.example.ntp_app.outlineDarkMediumContrast
import com.example.ntp_app.outlineLight
import com.example.ntp_app.outlineLightHighContrast
import com.example.ntp_app.outlineLightMediumContrast
import com.example.ntp_app.outlineVariantDark
import com.example.ntp_app.outlineVariantDarkHighContrast
import com.example.ntp_app.outlineVariantDarkMediumContrast
import com.example.ntp_app.outlineVariantLight
import com.example.ntp_app.outlineVariantLightHighContrast
import com.example.ntp_app.outlineVariantLightMediumContrast
import com.example.ntp_app.primaryContainerDark
import com.example.ntp_app.primaryContainerDarkHighContrast
import com.example.ntp_app.primaryContainerDarkMediumContrast
import com.example.ntp_app.primaryContainerLight
import com.example.ntp_app.primaryContainerLightHighContrast
import com.example.ntp_app.primaryContainerLightMediumContrast
import com.example.ntp_app.primaryDark
import com.example.ntp_app.primaryDarkHighContrast
import com.example.ntp_app.primaryDarkMediumContrast
import com.example.ntp_app.primaryLight
import com.example.ntp_app.primaryLightHighContrast
import com.example.ntp_app.primaryLightMediumContrast
import com.example.ntp_app.scrimDark
import com.example.ntp_app.scrimDarkHighContrast
import com.example.ntp_app.scrimDarkMediumContrast
import com.example.ntp_app.scrimLight
import com.example.ntp_app.scrimLightHighContrast
import com.example.ntp_app.scrimLightMediumContrast
import com.example.ntp_app.secondaryContainerDark
import com.example.ntp_app.secondaryContainerDarkHighContrast
import com.example.ntp_app.secondaryContainerDarkMediumContrast
import com.example.ntp_app.secondaryContainerLight
import com.example.ntp_app.secondaryContainerLightHighContrast
import com.example.ntp_app.secondaryContainerLightMediumContrast
import com.example.ntp_app.secondaryDark
import com.example.ntp_app.secondaryDarkHighContrast
import com.example.ntp_app.secondaryDarkMediumContrast
import com.example.ntp_app.secondaryLight
import com.example.ntp_app.secondaryLightHighContrast
import com.example.ntp_app.secondaryLightMediumContrast
//import com.example.ntp_app.surfaceBrightDarkHighContrast
//import com.example.ntp_app.surfaceContainerDarkHighContrast
//import com.example.ntp_app.surfaceContainerHighDarkHighContrast
//import com.example.ntp_app.surfaceContainerHighestDarkHighContrast
//import com.example.ntp_app.surfaceContainerLowDarkHighContrast
//import com.example.ntp_app.surfaceContainerLowestDarkHighContrast
import com.example.ntp_app.surfaceDark
import com.example.ntp_app.surfaceDarkHighContrast
import com.example.ntp_app.surfaceDarkMediumContrast
//import com.example.ntp_app.surfaceDimDarkHighContrast
import com.example.ntp_app.surfaceLight
import com.example.ntp_app.surfaceLightHighContrast
import com.example.ntp_app.surfaceLightMediumContrast
import com.example.ntp_app.surfaceVariantDark
import com.example.ntp_app.surfaceVariantDarkHighContrast
import com.example.ntp_app.surfaceVariantDarkMediumContrast
import com.example.ntp_app.surfaceVariantLight
import com.example.ntp_app.surfaceVariantLightHighContrast
import com.example.ntp_app.surfaceVariantLightMediumContrast
import com.example.ntp_app.tertiaryContainerDark
import com.example.ntp_app.tertiaryContainerDarkHighContrast
import com.example.ntp_app.tertiaryContainerDarkMediumContrast
import com.example.ntp_app.tertiaryContainerLight
import com.example.ntp_app.tertiaryContainerLightHighContrast
import com.example.ntp_app.tertiaryContainerLightMediumContrast
import com.example.ntp_app.tertiaryDark
import com.example.ntp_app.tertiaryDarkHighContrast
import com.example.ntp_app.tertiaryDarkMediumContrast
import com.example.ntp_app.tertiaryLight
import com.example.ntp_app.tertiaryLightHighContrast
import com.example.ntp_app.tertiaryLightMediumContrast
//
//@Immutable
//data class ExtendedColorScheme(
//    val notUse: String
//)

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
//    surfaceDim = surfaceDimLight,
//    surfaceBright = surfaceBrightLight,
//    surfaceContainerLowest = surfaceContainerLowestLight,
//    surfaceContainerLow = surfaceContainerLowLight,
//    surfaceContainer = surfaceContainerLight,
//    surfaceContainerHigh = surfaceContainerHighLight,
//    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme: ColorScheme
    get() = darkColorScheme(
        primary = primaryDark,
        onPrimary = onPrimaryDark,
        primaryContainer = primaryContainerDark,
        onPrimaryContainer = onPrimaryContainerDark,
        secondary = secondaryDark,
        onSecondary = onSecondaryDark,
        secondaryContainer = secondaryContainerDark,
        onSecondaryContainer = onSecondaryContainerDark,
        tertiary = tertiaryDark,
        onTertiary = onTertiaryDark,
        tertiaryContainer = tertiaryContainerDark,
        onTertiaryContainer = onTertiaryContainerDark,
        error = errorDark,
        onError = onErrorDark,
        errorContainer = errorContainerDark,
        onErrorContainer = onErrorContainerDark,
        background = backgroundDark,
        onBackground = onBackgroundDark,
        surface = surfaceDark,
        onSurface = onSurfaceDark,
        surfaceVariant = surfaceVariantDark,
        onSurfaceVariant = onSurfaceVariantDark,
        outline = outlineDark,
        outlineVariant = outlineVariantDark,
        scrim = scrimDark,
        inverseSurface = inverseSurfaceDark,
        inverseOnSurface = inverseOnSurfaceDark,
        inversePrimary = inversePrimaryDark,
    //    surfaceDim = surfaceDimDark,
    //    surfaceBright = surfaceBrightDark,
    //    surfaceContainerLowest = surfaceContainerLowestDark,
    //    surfaceContainerLow = surfaceContainerLowDark,
    //    surfaceContainer = surfaceContainerDark,
    //    surfaceContainerHigh = surfaceContainerHighDark,
    //    surfaceContainerHighest = surfaceContainerHighestDark,
    )


private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
//    surfaceDim = surfaceDimLightMediumContrast,
//    surfaceBright = surfaceBrightLightMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
//    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
//    surfaceContainer = surfaceContainerLightMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
//    surfaceDim = surfaceDimLightHighContrast,
//    surfaceBright = surfaceBrightLightHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
//    surfaceContainerLow = surfaceContainerLowLightHighContrast,
//    surfaceContainer = surfaceContainerLightHighContrast,
//    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
//    surfaceDim = surfaceDimDarkMediumContrast,
//    surfaceBright = surfaceBrightDarkMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
//    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
//    surfaceContainer = surfaceContainerDarkMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
//    surfaceDim = surfaceDimDarkHighContrast,
//    surfaceBright = surfaceBrightDarkHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
//    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
//    surfaceContainer = surfaceContainerDarkHighContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

//val extendedLight = ExtendedColorScheme(
//)
//
//val extendedDark = ExtendedColorScheme(
//)
//
//val extendedLightMediumContrast = ExtendedColorScheme(
//)
//
//val extendedLightHighContrast = ExtendedColorScheme(
//)
//
//val extendedDarkMediumContrast = ExtendedColorScheme(
//)
//
//val extendedDarkHighContrast = ExtendedColorScheme(
//)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppNTPTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
