package com.example.mytaskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.mytaskapp.screens.SplashScreen
import com.example.mytaskapp.screens.TaskScreen
import com.example.mytaskapp.ui.theme.MyTestApplicationTheme
import com.example.mytaskapp.view_models.TaskViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
  private val viewModel by viewModels<TaskViewModel>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyTestApplicationTheme {
        val showSplashScreen = remember {
          mutableStateOf(true)
        }
        LaunchedEffect(key1 = Unit, block = {
          delay(2000)
          showSplashScreen.value = false
        })

        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.primary
        ) {
          if (showSplashScreen.value)
            SplashScreen()
          else
            TaskScreen(viewModel)
        }
      }
    }
  }
}
