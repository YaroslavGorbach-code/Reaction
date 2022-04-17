package yaroslavgorbach.reaction.data.exercise.numbersLetters.model

import yaroslavgorbach.reaction.feature.exercise.common.model.YesNoChoseVariations

data class NumberAndLetter(val letter: Letter, val number: Number, val taskVariant: NumberAndLetterTaskVariant) {

    companion object {
        val Test = NumberAndLetter(Letter.Test, Number.Test, NumberAndLetterTaskVariant.VOWEL_LETTER)
    }

    override fun toString(): String {
        return "${number.number} | ${letter.letter}".uppercase()
    }

    inline fun checkAnswer(answer: YesNoChoseVariations, onResult: (isCorrect: Boolean) -> Unit) {
        when (answer) {
            YesNoChoseVariations.NO -> {
                when (taskVariant) {
                    NumberAndLetterTaskVariant.VOWEL_LETTER -> onResult(letter.isVowel.not())
                    NumberAndLetterTaskVariant.EVEN_NUMBER -> onResult(number.isEven().not())
                }
            }
            YesNoChoseVariations.YES -> {
                when (taskVariant) {
                    NumberAndLetterTaskVariant.VOWEL_LETTER -> onResult(letter.isVowel)
                    NumberAndLetterTaskVariant.EVEN_NUMBER -> onResult(number.isEven())
                }
            }
        }
    }
}