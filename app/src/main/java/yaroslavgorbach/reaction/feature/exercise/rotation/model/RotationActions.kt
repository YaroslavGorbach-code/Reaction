package yaroslavgorbach.reaction.feature.exercise.rotation.model

import yaroslavgorbach.reaction.feature.exercise.common.model.YesNoChoseVariations

sealed class RotationActions {
    class OnChose(val chose: YesNoChoseVariations) : RotationActions()
    object Back : RotationActions()
    object Repeat : RotationActions()
    object FinishExercise : RotationActions()
}