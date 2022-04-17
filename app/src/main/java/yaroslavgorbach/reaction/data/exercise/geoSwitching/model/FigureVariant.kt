package yaroslavgorbach.reaction.data.exercise.geoSwitching.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stop
import androidx.compose.ui.graphics.vector.ImageVector

enum class FigureVariant(val icon: ImageVector) {
    SQUARE(Icons.Filled.Stop), CIRCLE(Icons.Filled.Circle), STAR(Icons.Default.Star)

}