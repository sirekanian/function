package org.sirekanyan.`fun`

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.S
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import org.sirekanyan.`fun`.ui.layout.WindowWidthSizeClass
import org.sirekanyan.`fun`.ui.theme.DarkColorScheme
import org.sirekanyan.`fun`.ui.theme.LightColorScheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            App(getColorScheme(), WindowWidthSizeClass.Compact)
        }
    }
}

@Composable
private fun getColorScheme(): ColorScheme {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    return when {
        SDK_INT >= S && isDarkTheme -> dynamicDarkColorScheme(context)
        SDK_INT >= S -> dynamicLightColorScheme(context)
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
}
