package yaroslavgorbach.reaction.feature.exercise.rotation.model

import yaroslavgorbach.reaction.feature.exercise.common.model.WinRule

object RotationWinRule : WinRule {
    override val minRounds: Int
        get() = 40

    override val minCorrectPresent: Int
        get() = 90
}