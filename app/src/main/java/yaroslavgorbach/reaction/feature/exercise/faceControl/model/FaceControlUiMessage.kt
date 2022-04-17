package yaroslavgorbach.reaction.feature.exercise.faceControl.model

sealed class FaceControlUiMessage {
    object AnswerIsCorrect: FaceControlUiMessage()

    object AnswerIsNotCorrect: FaceControlUiMessage()
}