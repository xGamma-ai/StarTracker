package com.alwinsden.startrackernative.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun StarTrackerNativeTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography,
        content = content,
    )
}