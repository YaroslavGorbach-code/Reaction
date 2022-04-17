package yaroslavgorbach.reaction.feature.exercise.extraNumber.model

sealed class ExtraNumberUiMessage {
    object AnswerIsCorrect: ExtraNumberUiMessage()
    object AnswerIsNotCorrect: ExtraNumberUiMessage()
}