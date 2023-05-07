/**
 * Created by Jimmy McBride on 2023-05-01
 *
 * Copyright © 2023 Jimmy McBride
 */
package com.example.mytaskapp.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytaskapp.models.Task
import com.example.mytaskapp.ui.theme.MEDIUM_PADDING
import com.example.mytaskapp.ui.theme.MyTestApplicationTheme
import com.example.mytaskapp.ui.theme.SMALL_PADDING
import java.util.UUID

/**
 * TaskCard
 *
 * @author Jimmy McBride on 2023-05-01.
 */
@Composable
fun TaskCard(
  task: Task,
  toggleCompleted: (Task) -> Unit,
) {
  Card(
    modifier = Modifier
      .padding(SMALL_PADDING)
      .fillMaxWidth(),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    )
  ) {
    Row(
      modifier = Modifier
        .padding(SMALL_PADDING)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(
        modifier = Modifier
          .padding(start = MEDIUM_PADDING)
          .weight(1f),
        text = task.body,
        style = TextStyle(
          textDecoration = if (task.completed) TextDecoration.LineThrough
          else TextDecoration.None
        )
      )
      Checkbox(checked = task.completed, onCheckedChange = {
        toggleCompleted(task)
      })
    }
  }
}

@Preview(showBackground = true)
@Composable
fun TaskCardPreview() {
  MyTestApplicationTheme {
    Column(
      modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
      TaskCard(
        Task(id = UUID.randomUUID(), "This is a task", false),
        toggleCompleted = {}
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LongTaskCardPreview() {
  MyTestApplicationTheme {
    Column(
      modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
      TaskCard(
        task = Task(
          id = UUID.randomUUID(),
          body = "Learn about all everything in the universe and build a course teaching teh one true theory of everything.",
          completed = false
        ), toggleCompleted = {})
    }
  }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkTaskCardPreview() {
  MyTestApplicationTheme {
    Column(
      modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
      TaskCard(
        Task(id = UUID.randomUUID(), "This is a task", true),
        toggleCompleted = {}
      )
    }
  }
}
