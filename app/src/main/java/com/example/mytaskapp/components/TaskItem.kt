/**
 * Created by Jimmy McBride on 2023-05-01
 *
 * Copyright © 2023 Jimmy McBride
 */
package com.example.mytaskapp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.mytaskapp.models.Task
import com.example.mytaskapp.view_models.TaskViewModel

/**
 * TaskItem
 *
 * @author Jimmy McBride on 2023-05-01.
 */
@Composable
fun TaskItem(
  viewModel: TaskViewModel,
  task: Task,
) {
  LaunchedEffect(key1 = true, block = {
    viewModel.deleteAllCompletedEvent.collect { taskList ->
      if (taskList.contains(task.id)) {
        viewModel.deleteTask(task.id)
      }
    }
  })

  TaskCard(
    task = task,
    toggleCompleted = viewModel::toggleTaskCompleted
  )
}
