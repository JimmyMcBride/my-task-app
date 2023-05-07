package com.example.mytaskapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorScheme.backgroundColor
  @Composable
  get() = if (isSystemInDarkTheme()) Gray800 else Gray300

val Gray300 = Color(0xFFE0E0E0)
val Gray800 = Color(0xFF424242)

val Red400 = Color(0xFFEF5350)
val Red800 = Color(0xFFC62828)

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)