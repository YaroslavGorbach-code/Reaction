package yaroslavgorbach.reaction.feature.exercise.common.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SentimentDissatisfied
import androidx.compose.material.icons.outlined.SentimentSatisfied
import androidx.compose.ui.graphics.vector.ImageVector
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.common.mapper.ExerciseNameToWinRuleMapper

data class FinishExerciseState(
    val name: ExerciseName,
    val isFinished: Boolean = false,
    val pointsCorrect: Int = 0,
    val pointsIncorrect: Int = 0,
) {
    val winRule = ExerciseNameToWinRuleMapper.map(name)

    val summaryPoints: Int
        get() = pointsCorrect + pointsIncorrect

    val correctPresent: Float
        get() = ((pointsCorrect.toFloat() / (pointsIncorrect.toFloat() + pointsCorrect.toFloat())))

    val icon: ImageVector
        get() = if (isWin) Icons.Outlined.SentimentSatisfied else Icons.Outlined.SentimentDissatisfied

    val isWin: Boolean
        get() = correctPresent > winRule.minCorrectPresent / 100f && summaryPoints >= winRule.minRounds

    val progressString: String
        get() = "${(correctPresent * 100).toInt()} %"
}
