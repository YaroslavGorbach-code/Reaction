package yaroslavgorbach.reaction.data.exercise.stroop.model

import androidx.compose.ui.graphics.Color

data class StroopWord(val text: String, val color: WordColorVariant) {
    companion object {
        val Test = StroopWord("Красный", WordColorVariant.GREEN)
        val Empty = StroopWord("", WordColorVariant.GREEN)
    }

    suspend fun checkAnswer(wordColorVariant: WordColorVariant, onResult: suspend (isCorrect: Boolean) -> Unit) {
        onResult(wordColorVariant == color)
    }
}