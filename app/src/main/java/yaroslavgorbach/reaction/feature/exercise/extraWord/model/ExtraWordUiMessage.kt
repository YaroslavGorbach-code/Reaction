package yaroslavgorbach.reaction.feature.exercise.extraWord.model

sealed class ExtraWordUiMessage {
    object AnswerIsCorrect: ExtraWordUiMessage()

    object AnswerIsNotCorrect: ExtraWordUiMessage()
}