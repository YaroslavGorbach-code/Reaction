package yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model

import yaroslavgorbach.reaction.data.exercise.complexSort.model.ComplexSortItem
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState
import yaroslavgorbach.reaction.utill.TimerCountDown
import yaroslavgorbach.reaction.utill.UiMessage

data class ComplexSortViewState(
    val items: List<ComplexSortItem> = emptyList(),
    val timerState: TimerCountDown.TimerState = TimerCountDown.TimerState.Tick(0, "00:00", 0f),
    val finishExerciseState: FinishExerciseState = FinishExerciseState(name = ExerciseName.COMPLEX_SORT),
    val uiMessage: UiMessage<ComplexSortUiMessage>? = null
) {
    companion object {
        val Empty = ComplexSortViewState()
        val Test = ComplexSortViewState(items = listOf(ComplexSortItem.Test))
    }
}
