package yaroslavgorbach.reaction.data.exercise.faceControl.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SentimentSatisfied
import androidx.compose.ui.graphics.vector.ImageVector

data class Face(val icon: ImageVector, val isDissatisfied: Boolean) {

    companion object {
        val Test: Face
            get() = Face(Icons.Outlined.SentimentSatisfied, false)
    }
}