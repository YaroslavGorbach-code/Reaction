package yaroslavgorbach.reaction.data.exercise.extraWord.model

import kotlin.random.Random

data class Word(val word: String, val isExtra: Boolean) {
    companion object {
        val Test
            get() = Word("АБВ", Random.nextBoolean())
    }
}
