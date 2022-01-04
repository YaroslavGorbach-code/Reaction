package yaroslavgorbach.reaction.feature.exercise.geoSwitching.model

import yaroslavgorbach.reaction.data.exercise.complexSort.model.ComplexSortItem
import yaroslavgorbach.reaction.data.exercise.geoSwitching.model.FigureVariant
import yaroslavgorbach.reaction.data.exercise.geoSwitching.model.GeoFigure
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState
import yaroslavgorbach.reaction.utill.TimerCountDown

data class GeoSwitchingViewState(
    val figure: GeoFigure = GeoFigure.Test,
    val timerState: TimerCountDown.TimerState = TimerCountDown.TimerState.Tick(0, "00:00", 0f),
    val finishExerciseState: FinishExerciseState = FinishExerciseState(name = ExerciseName.GEO_SWITCHING)
) {
    companion object {
        val Test = GeoSwitchingViewState()
    }
}
