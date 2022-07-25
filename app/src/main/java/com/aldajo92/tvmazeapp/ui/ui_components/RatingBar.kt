package com.aldajo92.tvmazeapp.ui.ui_components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * Open Source References: https://gist.github.com/vitorprado/0ae4ad60c296aefafba4a157bb165e60
 */

@Preview
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float = 2.5f,
    color: Color = MaterialTheme.colors.primary
) {
    Row(modifier = modifier.wrapContentSize()) {
        (1..5).forEach { step ->
            val stepRating = when {
                rating > step -> 1f
                step.rem(rating) < 1 -> rating - (step - 1f)
                else -> 0f
            }
            RatingStar(stepRating, color)
        }
    }
}

@Composable
private fun RatingStar(
    rating: Float,
    ratingColor: Color = MaterialTheme.colors.primary,
    backgroundColor: Color = Color.Gray
) {
    BoxWithConstraints(
        modifier = Modifier
          .fillMaxHeight()
          .aspectRatio(1f)
          .clip(starShape)
    ) {
        Canvas(modifier = Modifier.size(maxHeight)) {
            drawRect(
                brush = SolidColor(backgroundColor),
                size = Size(
                    height = size.height * 1.4f,
                    width = size.width * 1.4f
                ),
                topLeft = Offset(
                    x = -(size.width * 0.1f),
                    y = -(size.height * 0.1f)
                )
            )
            if (rating > 0) {
                drawRect(
                    brush = SolidColor(ratingColor),
                    size = Size(
                        height = size.height * 1.1f,
                        width = size.width * rating
                    )
                )
            }
        }
    }
}

private val starShape = GenericShape { size, _ ->
    addPath(starPath(size.height))
}

private val starPath = { size: Float ->
    Path().apply {
        val outerRadius = size / 1.8f
        val innerRadius = outerRadius / 2.5
        var rotation = Math.PI / 2 * 3
        val cx = size / 2
        val cy = size / 20 * 11
        var x = cx
        var y = cy
        val step = Math.PI / 5

        moveTo(cx, cy - outerRadius)
        repeat(5) {
            x = (cx + cos(rotation) * outerRadius).toFloat()
            y = (cy + sin(rotation) * outerRadius).toFloat()
            lineTo(x, y)
            rotation += step

            x = (cx + cos(rotation) * innerRadius).toFloat()
            y = (cy + sin(rotation) * innerRadius).toFloat()
            lineTo(x, y)
            rotation += step
        }
        close()
    }
}

@Preview
@Composable
fun RatingBarPreview() {
    Column(
      Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        RatingBar(
            modifier = Modifier.height(20.dp),
            3.5f,
        )
    }
}
