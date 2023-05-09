/**
 * Created by Jimmy McBride on 2023-05-01
 *
 * Copyright © 2023 Jimmy McBride
 */
package com.example.mytaskapp.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytaskapp.components.AddTaskInput
import com.example.mytaskapp.components.TaskAppTopBar
import com.example.mytaskapp.components.TaskItem
import com.example.mytaskapp.ui.theme.MyTestApplicationTheme
import com.example.mytaskapp.utils.deleteAllTasksDialog
import com.example.mytaskapp.view_models.TaskViewModel

/**
 * TaskScreen
 *
 * @author Jimmy McBride on 2023-05-01.
 */
@Composable
fun TaskScreen(viewModel: TaskViewModel) {
  val isInputVisible = remember {
    mutableStateOf(false)
  }
  val deleteAllTasksDialog = remember {
    mutableStateOf(false)
  }.deleteAllTasksDialog(viewModel)

  Scaffold(
    topBar = {
      TaskAppTopBar(deleteAllTasksDialog)
    },
    floatingActionButton = {
      IconButton(
        colors = IconButtonDefaults.filledIconButtonColors(
          containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
          isInputVisible.value = !isInputVisible.value
        },
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Add Icon",
          tint = MaterialTheme.colorScheme.onPrimary,
        )
      }
    }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxSize()
        .padding(paddingValues)
        .imePadding(),
    ) {
      if (isInputVisible.value)
        AddTaskInput(viewModel, isInputVisible)
      LazyColumn(
        modifier = Modifier.weight(1f),
        content = {
          items(items = viewModel.taskList, key = {
            it.id
          }) { task ->
            TaskItem(viewModel, task)
          }
        })
    }
  }
}

@Preview
@Composable
fun TaskScreenPreview() {
  MyTestApplicationTheme {
    TaskScreen(viewModel = TaskViewModel())
  }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkTaskScreenPreview() {
  MyTestApplicationTheme {
    TaskScreen(viewModel = TaskViewModel())
  }
}
