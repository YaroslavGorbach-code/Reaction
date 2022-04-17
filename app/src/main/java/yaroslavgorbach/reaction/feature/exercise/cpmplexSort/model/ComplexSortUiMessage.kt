package yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model

sealed class ComplexSortUiMessage {
    object AnswerIsCorrect: ComplexSortUiMessage()

    object AnswerIsNotCorrect: ComplexSortUiMessage()
}