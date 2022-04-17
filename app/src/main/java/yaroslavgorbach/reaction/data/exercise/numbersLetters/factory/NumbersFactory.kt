package yaroslavgorbach.reaction.data.exercise.numbersLetters.factory

import yaroslavgorbach.reaction.data.exercise.numbersLetters.model.Number

class NumbersFactory {
    private val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    fun create(): List<Number> = numbers.map(::Number)
}