/**
 * Created by Jimmy McBride on 2023-04-27
 *
 * Copyright © 2023 Jimmy McBride
 */
package com.example.mytaskapp.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.mytaskapp.ui.theme.ANIMATION_DURATION

/**
 * ExpandAndShrinkAnimation
 *
 * @author Jimmy McBride on 2023-04-27.
 */
@Composable
fun MutableState<Boolean>.ExpandAndShrinkAnimation(
  content: @Composable () -> Unit,
) {
  AnimatedVisibility(
    visible = this.value,
    enter = expandVertically(
      animationSpec = tween(
        durationMillis = ANIMATION_DURATION
      )
    ),
    exit = shrinkVertically(
      animationSpec = tween(
        durationMillis = ANIMATION_DURATION
      )
    )
  ) {
    content()
  }
}
