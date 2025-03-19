package com.ato.minibabilonlibrary.ui.componsents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ExpandableBlock(
    isVisible: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(horizontalAlignment = Alignment.Start) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) { onToggle() }
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Toggle",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .rotate(if (isVisible) 180f else 0f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                AnimatedVisibility(
                    visible = !isVisible,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Text("Library settings")
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(visible = isVisible) {
            content()
        }
    }
}

/**
 * Число символов в одной книге: 410 × 40 × 80 = 1 312 000;
 * Число символов в алфавите: 25;
 * Если учитывать, что в Вавилонской библиотеке невозможны две одинаковые книги, число книг будет равно числу вариантов расположения знаков в книге: 251 312 000. Это число называют числом Борхеса[1].
 */

