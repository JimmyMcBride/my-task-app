/**
 * Created by Jimmy McBride on 2023-05-07
 *
 * Copyright © 2023 Jimmy McBride
 */
package com.example.mytaskapp.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.mytaskapp.ui.theme.ANIMATION_DURATION

/**
 * SlideInAndOutAnimation
 *
 * @author Jimmy McBride on 2023-05-07.
 */
@Composable
fun MutableState<Boolean>.SlideInAndOutAnimation(
  content: @Composable () -> Unit,
) {
  AnimatedVisibility(
    visible = this.value,
    enter = slideInVertically(
      animationSpec = tween(
        durationMillis = ANIMATION_DURATION
      ),
      initialOffsetY = { fullHeight -> fullHeight }
    ),
    exit = slideOutVertically(
      animationSpec = tween(
        durationMillis = ANIMATION_DURATION
      ),
      targetOffsetY = { fullHeight -> -fullHeight }
    )
  ) {
    content()
  }
}
