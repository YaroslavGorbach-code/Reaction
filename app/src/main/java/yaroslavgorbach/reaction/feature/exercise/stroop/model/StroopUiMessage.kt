package yaroslavgorbach.reaction.feature.exercise.stroop.model

sealed class StroopUiMessage {
    object AnswerIsCorrect: StroopUiMessage()

    object AnswerIsNotCorrect: StroopUiMessage()
}