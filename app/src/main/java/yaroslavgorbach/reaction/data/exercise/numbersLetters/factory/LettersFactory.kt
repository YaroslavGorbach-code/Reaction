package yaroslavgorbach.reaction.data.exercise.numbersLetters.factory

import android.content.Context
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercise.numbersLetters.model.Letter

class LettersFactory(context: Context) {

    private val letters = context.resources.getStringArray(R.array.letters)
    private val vowels = context.resources.getStringArray(R.array.letters_vowels)

    fun create(): List<Letter> {
        return letters.map { letter -> Letter(letter, vowels.any { it == letter }) }
    }
}