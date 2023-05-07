/**
 * Created by Jimmy McBride on 2023-05-07
 *
 * Copyright © 2023 Jimmy McBride
 */
package com.example.mytaskapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytaskapp.R

/**
 * SplashScreen
 *
 * @author Jimmy McBride on 2023-05-07.
 */
@Composable
fun SplashScreen() {
  Column(
    modifier = Modifier
      .fillMaxSize(),
//      .background(MaterialTheme.colorScheme.primary),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Image(
      painter = painterResource(id = R.drawable.todo_logo),
      contentDescription = null
    )
  }
}

@Preview
@Composable
fun PreviewSplashScreen() {
  SplashScreen()
}
