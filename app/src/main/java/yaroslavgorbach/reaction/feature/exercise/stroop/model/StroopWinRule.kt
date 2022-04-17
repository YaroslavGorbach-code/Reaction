package yaroslavgorbach.reaction.feature.exercise.stroop.model

import yaroslavgorbach.reaction.feature.exercise.common.model.WinRule

object StroopWinRule : WinRule {
    override val minRounds: Int
        get() = 70

    override val minCorrectPresent: Int
        get() = 95
}