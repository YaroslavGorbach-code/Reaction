package yaroslavgorbach.reaction.feature.exercise.extraNumber.model

import yaroslavgorbach.reaction.data.exercise.extraNumber.local.model.Number

sealed class ExtraNumberActions {
    class NumberClick(val number: Number) : ExtraNumberActions()
    object Back : ExtraNumberActions()
    object Repeat : ExtraNumberActions()
    object FinishExercise : ExtraNumberActions()
}