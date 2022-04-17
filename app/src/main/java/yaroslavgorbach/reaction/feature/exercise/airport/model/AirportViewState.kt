package yaroslavgorbach.reaction.feature.exercise.airport.model

import yaroslavgorbach.reaction.data.exercise.airport.model.Plane
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState
import yaroslavgorbach.reaction.feature.exercise.extraNumber.model.ExtraNumberUiMessage
import yaroslavgorbach.reaction.utill.TimerCountDown
import yaroslavgorbach.reaction.utill.UiMessage

data class AirportViewState(
    val plane: Plane = Plane.Test,
    val timerState: TimerCountDown.TimerState = TimerCountDown.TimerState.Tick(0, "00:00", 0f),
    val finishExerciseState: FinishExerciseState = FinishExerciseState(name = ExerciseName.AIRPORT),
    val message: UiMessage<AirportUiMessage>? = null
) {
    companion object {
        val Empty = AirportViewState()
        val Test = AirportViewState(Plane.Test)
    }
}
