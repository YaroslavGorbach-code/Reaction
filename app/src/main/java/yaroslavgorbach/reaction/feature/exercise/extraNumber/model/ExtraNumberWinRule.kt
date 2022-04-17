package yaroslavgorbach.reaction.feature.exercise.extraNumber.model

import yaroslavgorbach.reaction.feature.exercise.common.model.WinRule

object ExtraNumberWinRule : WinRule {
    override val minRounds: Int
        get() = 35

    override val minCorrectPresent: Int
        get() = 93
}