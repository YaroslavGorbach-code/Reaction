package yaroslavgorbach.reaction.feature.exercise.faceControl.model

import yaroslavgorbach.reaction.data.exercise.faceControl.model.Face

sealed class FaceControlActions {
    class FaceClick(val face: Face) : FaceControlActions()
    object Back : FaceControlActions()
    object Repeat : FaceControlActions()
    object FinishExercise : FaceControlActions()
}