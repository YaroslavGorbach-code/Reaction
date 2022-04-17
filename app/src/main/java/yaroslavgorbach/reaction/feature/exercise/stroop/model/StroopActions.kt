package yaroslavgorbach.reaction.feature.exercise.stroop.model

import yaroslavgorbach.reaction.data.exercise.stroop.model.WordColorVariant

sealed class StroopActions {
    class OnChose(val chose: WordColorVariant) : StroopActions()
    object Back : StroopActions()
    object Repeat : StroopActions()
    object FinishExercise : StroopActions()
}