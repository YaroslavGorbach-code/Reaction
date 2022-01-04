package yaroslavgorbach.reaction.feature.exercise.faceControl.model

import yaroslavgorbach.reaction.data.exercise.faceControl.model.FacePack
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState
import yaroslavgorbach.reaction.utill.TimerCountDown

data class FaceControlViewState(
    val facePacks: List<FacePack> = listOf(FacePack.Empty),
    val timerState: TimerCountDown.TimerState = TimerCountDown.TimerState.Tick(0, "00:00", 0f),
    val finishExerciseState: FinishExerciseState = FinishExerciseState(name = ExerciseName.FACE_CONTROL)
) {
    companion object {
        val Empty = FaceControlViewState()
    }
}
