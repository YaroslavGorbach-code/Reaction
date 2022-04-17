package yaroslavgorbach.reaction.feature.exercise.extraWord.model

import yaroslavgorbach.reaction.feature.exercise.common.model.WinRule

object ExtraWordWinRule : WinRule {
    override val minRounds: Int
        get() = 30

    override val minCorrectPresent: Int
        get() = 93
}