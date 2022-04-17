package yaroslavgorbach.reaction.feature.description.model

import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName

sealed class DescriptionActions {
    data class OpenExercise(val exerciseName: ExerciseName) : DescriptionActions()
    object Back : DescriptionActions()
}