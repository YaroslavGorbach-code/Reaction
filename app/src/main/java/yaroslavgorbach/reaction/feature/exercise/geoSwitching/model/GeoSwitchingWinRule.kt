package yaroslavgorbach.reaction.feature.exercise.geoSwitching.model

import yaroslavgorbach.reaction.feature.exercise.common.model.WinRule

object GeoSwitchingWinRule : WinRule {
    override val minRounds: Int
        get() = 50

    override val minCorrectPresent: Int
        get() = 95
}