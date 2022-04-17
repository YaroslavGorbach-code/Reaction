package yaroslavgorbach.reaction.feature.exercise.airport.model

import yaroslavgorbach.reaction.feature.exercise.common.model.WinRule

object AirportWinRule : WinRule {
    override val minRounds: Int
        get() = 55

    override val minCorrectPresent: Int
        get() = 95
}