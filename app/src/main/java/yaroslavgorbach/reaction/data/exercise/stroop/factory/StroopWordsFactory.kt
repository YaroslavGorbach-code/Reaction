package yaroslavgorbach.reaction.data.exercise.stroop.factory

import android.content.Context
import androidx.compose.ui.graphics.Color
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercise.stroop.model.StroopWord
import yaroslavgorbach.reaction.data.exercise.stroop.model.WordColorVariant

@ExperimentalStdlibApi
class StroopWordsFactory(val context: Context) {
    private val words = listOf(context.getString(R.string.red), context.getString(R.string.green))
    private val colors = listOf(WordColorVariant.GREEN, WordColorVariant.RED)

    fun create(): List<StroopWord> {
        return buildList {
            repeat(1000) {
                this.add(StroopWord(text = words.random(), color = colors.random()))
            }
        }
    }
}