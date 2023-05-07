package com.example.mytaskapp.models

import java.util.UUID

/**
 * Created by Jimmy McBride on 2023-04-26
 *
 * Copyright © 2023 Jimmy McBride
 */
data class Task(
  val id: UUID,
  val body: String,
  val completed: Boolean,
)
