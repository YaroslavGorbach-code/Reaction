package yaroslavgorbach.reaction.data.exercise.extraWord.factory

import android.content.Context
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercise.extraWord.model.Word
import yaroslavgorbach.reaction.data.exercise.extraWord.model.WordPack
import kotlin.random.Random

@ExperimentalStdlibApi
class WordPacksFactory(context: Context) {
    companion object{
        private const val WORDS_IN_A_PACK = 25
    }

    private val words = context.resources.getStringArray(R.array.extra_words)

    fun create(): List<WordPack> {
        return buildList {
            repeat(1000) {
                this.add(
                    WordPack(buildList {
                        var extraWord = words.random()
                        val word = words.random()
                        val extraIndex = Random.nextInt(WORDS_IN_A_PACK)

                        while (extraWord == word) {
                            extraWord = words.random()
                        }

                        repeat(WORDS_IN_A_PACK) { index ->
                            if (index == extraIndex) {
                                this.add(Word(extraWord, true))
                            } else {
                                this.add(Word(word, false))
                            }
                        }
                    })
                )
            }
        }
    }
}