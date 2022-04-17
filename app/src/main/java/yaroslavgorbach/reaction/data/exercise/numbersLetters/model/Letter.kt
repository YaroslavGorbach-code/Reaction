package yaroslavgorbach.reaction.data.exercise.numbersLetters.model

data class Letter(val letter: String, val isVowel: Boolean){
    companion object{
        val Test = Letter("A", true)
    }
}