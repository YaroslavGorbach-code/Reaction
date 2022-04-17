package yaroslavgorbach.reaction.feature.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressIndicatorWithContent(
    modifier: Modifier,
    progress: Float,
    strokeColor: Color = Color.Green,
    backgroundStrokeColor: Color = Color.LightGray,
    strokeWidth: Dp = 1.dp,
    backgroundStrokeWidth: Dp = 1.dp,
    isVisible: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = modifier) {
        if (isVisible) {
            CircularProgressIndicator(
                progress = progress,
                color = strokeColor,
                strokeWidth = strokeWidth,
                modifier = Modifier.drawBehind {
                    drawCircle(
                        color = backgroundStrokeColor,
                        radius = (size.maxDimension / 2.0f) - (strokeWidth.value),
                        style = Stroke(width = backgroundStrokeWidth.toPx())
                    )
                }
            )
        }
        content()
    }
}