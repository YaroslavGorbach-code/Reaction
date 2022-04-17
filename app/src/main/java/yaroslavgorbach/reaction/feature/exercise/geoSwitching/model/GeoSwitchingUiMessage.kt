package yaroslavgorbach.reaction.feature.exercise.geoSwitching.model

sealed class GeoSwitchingUiMessage {
    object AnswerIsCorrect: GeoSwitchingUiMessage()

    object AnswerIsNotCorrect: GeoSwitchingUiMessage()
}