package yaroslavgorbach.reaction.feature.exercise.numbersAndLetters.model

sealed class NumbersAndLettersUiMessage {
    object AnswerIsCorrect: NumbersAndLettersUiMessage()

    object AnswerIsNotCorrect: NumbersAndLettersUiMessage()
}