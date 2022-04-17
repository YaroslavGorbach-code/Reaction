package yaroslavgorbach.reaction.feature.exercise.numbersAndLetters.model

import yaroslavgorbach.reaction.feature.exercise.common.model.WinRule

object NumbersAndLettersWinRule : WinRule {
    override val minRounds: Int
        get() = 45

    override val minCorrectPresent: Int
        get() = 95
}