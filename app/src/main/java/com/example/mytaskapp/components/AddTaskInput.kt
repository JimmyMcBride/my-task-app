/**
 * Created by Jimmy McBride on 2023-05-01
 *
 * Copyright © 2023 Jimmy McBride
 */
package com.example.mytaskapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.example.mytaskapp.ui.theme.MEDIUM_PADDING
import com.example.mytaskapp.ui.theme.SMALL_PADDING
import com.example.mytaskapp.view_models.TaskViewModel

/**
 * AddTaskInput
 *
 * @author Jimmy McBride on 2023-05-01.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTaskInput(
  viewModel: TaskViewModel,
  isInputVisible: MutableState<Boolean>,
) {
  val focusManager = LocalFocusManager.current
  val keyboardController = LocalSoftwareKeyboardController.current
  val focusRequester = remember { FocusRequester() }

  LaunchedEffect(Unit) {
    if (isInputVisible.value) {
      focusRequester.requestFocus()
    }
  }

  var body by remember {
    mutableStateOf("")
  }

  var error by remember { mutableStateOf("") }
  var isErrorVisible by remember { mutableStateOf(false) }

  Column(modifier = Modifier.padding(MEDIUM_PADDING, SMALL_PADDING)) {
    OutlinedTextField(
      modifier = Modifier
        .fillMaxWidth()
        .focusRequester(focusRequester),
      value = body,
      onValueChange = {
        isErrorVisible = false
        body = it
      },
      label = { Text("Enter task") },
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      keyboardActions = KeyboardActions(
        onDone = {
          val (hasError, errorMessage) = viewModel.addTask(body)
          error = errorMessage
          isErrorVisible = hasError

          if (!hasError) {
            body = ""
            isInputVisible.value = false
            keyboardController?.hide()
            focusManager.clearFocus()
          }
        }),
      isError = isErrorVisible,
    )
    if (isErrorVisible) {
      Text(text = error, color = MaterialTheme.colorScheme.error)
    }
  }
}
