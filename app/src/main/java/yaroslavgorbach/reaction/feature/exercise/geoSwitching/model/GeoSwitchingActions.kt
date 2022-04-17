package yaroslavgorbach.reaction.feature.exercise.geoSwitching.model

import yaroslavgorbach.reaction.feature.exercise.common.model.YesNoChoseVariations

sealed class GeoSwitchingActions {
    class Chose(val yesNoChose: YesNoChoseVariations) : GeoSwitchingActions()
    object Back : GeoSwitchingActions()
    object Repeat : GeoSwitchingActions()
    object FinishExercise : GeoSwitchingActions()
}