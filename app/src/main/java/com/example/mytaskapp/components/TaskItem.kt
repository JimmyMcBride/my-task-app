/**
 * Created by Jimmy McBride on 2023-05-01
 *
 * Copyright © 2023 Jimmy McBride
 */
package com.example.mytaskapp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import com.example.mytaskapp.models.Task
import com.example.mytaskapp.ui.theme.ANIMATION_DURATION
import com.example.mytaskapp.ui.theme.SMALL_PADDING
import com.example.mytaskapp.utils.ExpandAndShrinkAnimation
import com.example.mytaskapp.view_models.TaskViewModel
import kotlinx.coroutines.delay

/**
 * TaskItem
 *
 * @author Jimmy McBride on 2023-05-01.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
  viewModel: TaskViewModel,
  task: Task,
) {
  val dismissState = rememberDismissState()
  val isDismissed =
    dismissState.isDismissed(DismissDirection.StartToEnd)

  val itemAppeared = remember { mutableStateOf(false) }

  LaunchedEffect(
    key1 = true,
  ) {
    itemAppeared.value = true
  }

  LaunchedEffect(
    key1 = dismissState.isDismissed(DismissDirection.StartToEnd)
  ) {
    if (isDismissed) {
      itemAppeared.value = false
      delay(ANIMATION_DURATION.toLong())
      viewModel.deleteTask(task.id)
    }
  }

  LaunchedEffect(key1 = true, block = {
    viewModel.deleteAllCompletedEvent.collect { taskList ->
      if (taskList.contains(task.id)) {
        itemAppeared.value = false
        delay(ANIMATION_DURATION.toLong())
        viewModel.deleteTask(task.id)
      }
    }
  })


  itemAppeared.ExpandAndShrinkAnimation {
    SwipeToDismiss(
      state = dismissState,
      modifier = Modifier,
      directions = setOf(
        DismissDirection.StartToEnd
      ),
      background = {
        val color by animateColorAsState(
          when (dismissState.targetValue) {
            DismissValue.Default -> Color.White
            else -> MaterialTheme.colorScheme.error
          }
        )

        val rotate by animateFloatAsState(
          if (dismissState.targetValue == DismissValue.Default) 1f else 25f
        )

        Box(
          Modifier
            .fillMaxSize()
            .background(color)
            .padding(start = SMALL_PADDING),
          contentAlignment = Alignment.CenterStart
        ) {
          Icon(
            Icons.Default.Delete,
            contentDescription = "Delete Icon",
            modifier = Modifier.rotate(rotate)
          )
        }
      },
      dismissContent = {
        Column(
          modifier = Modifier
            .background(
              MaterialTheme.colorScheme.background
            )
        ) {
          TaskCard(
            task = task,
            toggleCompleted = viewModel::toggleTaskCompleted
          )
        }
      }
    )
  }
}
