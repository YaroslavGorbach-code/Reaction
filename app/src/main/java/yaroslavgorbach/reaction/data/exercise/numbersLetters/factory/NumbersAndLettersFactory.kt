package yaroslavgorbach.reaction.data.exercise.numbersLetters.factory

import android.content.Context
import yaroslavgorbach.reaction.data.exercise.numbersLetters.model.NumberAndLetter
import yaroslavgorbach.reaction.data.exercise.numbersLetters.model.NumberAndLetterTaskVariant

@ExperimentalStdlibApi
class NumbersAndLettersFactory(val context: Context) {

    private val numbersFactory: NumbersFactory
        get() = NumbersFactory()

    private val lettersFactory: LettersFactory
        get() = LettersFactory(context)

    private val numbers = numbersFactory.create()
    private val letters = lettersFactory.create()
    private val tasks = NumberAndLetterTaskVariant.values().toList()

    fun create(): List<NumberAndLetter> {
        return buildList {
            repeat(1000) {
                add(NumberAndLetter(letter = letters.random(), number = numbers.random(), tasks.random()))
            }
        }
    }
}