package yaroslavgorbach.reaction.feature.esercises.model

import yaroslavgorbach.reaction.data.exercises.local.model.Exercise
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.utill.UiMessage

data class ExercisesViewState(
    val exercises: List<Exercise> = emptyList(),
    val exerciseAvailabilityDialogState: ExerciseAvailabilityDialogState = ExerciseAvailabilityDialogState(),
    val isOnboardingDialogVisible: Boolean = false,
    val message: UiMessage<ExercisesUiMassage>? = null
) {

    data class ExerciseAvailabilityDialogState(
        val isVisible: Boolean = false,
        val exerciseName: ExerciseName = ExerciseName.NO_NAME
    )

    companion object {
        val Empty = ExercisesViewState()
    }
}
