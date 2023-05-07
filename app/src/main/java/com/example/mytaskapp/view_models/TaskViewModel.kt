/**
 * Created by Jimmy McBride on 2023-04-26
 *
 * Copyright © 2023 Jimmy McBride
 */
package com.example.mytaskapp.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytaskapp.models.Task
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID

/**
 * TaskViewModel
 *
 * @author Jimmy McBride on 2023-04-26.
 */
class TaskViewModel : ViewModel() {
  var taskList by mutableStateOf<List<Task>>(listOf())
    private set

  private val _deleteAllCompletedEvent = MutableSharedFlow<List<UUID>>()
  val deleteAllCompletedEvent = _deleteAllCompletedEvent.asSharedFlow()

  fun addTask(body: String): Pair<Boolean, String> {

    if (body.isBlank()) {
      return true to "Cannot add a blank task"
    }

    if (taskList.any { it.body == body }) {
      return true to "That task already exists"
    }

    taskList = taskList + Task(UUID.randomUUID(), body, completed = false)
    return false to ""
  }

  fun toggleTaskCompleted(task: Task) {
    val index = taskList.indexOf(task)
    val updatedTask =
      taskList[index].copy(completed = !taskList[index].completed)
    taskList = taskList.toMutableList().also {
      it[index] = updatedTask
    }
  }

  fun deleteCompletedTasks() {
    val newTaskList = taskList.filter { it.completed }.map { it.id }
    viewModelScope.launch {
      _deleteAllCompletedEvent.emit(
        newTaskList
      )
    }
  }

  fun deleteTask(taskId: UUID) {
    taskList = taskList.filter { it.id != taskId }
  }
}
