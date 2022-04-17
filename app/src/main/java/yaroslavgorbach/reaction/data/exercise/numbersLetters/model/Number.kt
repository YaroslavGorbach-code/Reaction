package yaroslavgorbach.reaction.data.exercise.numbersLetters.model

data class Number(val number: Int) {

    companion object {
        val Test = Number(30)
    }

    fun isEven(): Boolean = number % 2 == 0
}