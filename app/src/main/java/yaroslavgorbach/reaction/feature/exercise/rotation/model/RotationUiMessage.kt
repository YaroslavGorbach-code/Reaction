package yaroslavgorbach.reaction.feature.exercise.rotation.model

sealed class RotationUiMessage {
    object AnswerIsCorrect: RotationUiMessage()

    object AnswerIsNotCorrect: RotationUiMessage()
}