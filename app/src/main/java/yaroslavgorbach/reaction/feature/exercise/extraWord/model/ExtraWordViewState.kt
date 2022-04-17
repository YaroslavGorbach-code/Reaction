package yaroslavgorbach.reaction.feature.exercise.extraWord.model

import yaroslavgorbach.reaction.data.exercise.extraWord.model.WordPack
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.airport.model.AirportUiMessage
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState
import yaroslavgorbach.reaction.utill.TimerCountDown
import yaroslavgorbach.reaction.utill.UiMessage

data class ExtraWordViewState(
    val wordPacks: List<WordPack> = listOf(WordPack.Empty),
    val timerState: TimerCountDown.TimerState = TimerCountDown.TimerState.Tick(0, "00:00", 0f),
    val finishExerciseState: FinishExerciseState = FinishExerciseState(name = ExerciseName.EXTRA_WORD),
    val message: UiMessage<ExtraWordUiMessage>? = null
) {
    companion object {
        val Empty = ExtraWordViewState()
    }
}
