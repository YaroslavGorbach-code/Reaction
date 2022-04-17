package yaroslavgorbach.reaction.feature.exercise.airport.model

sealed class AirportUiMessage {
    object AnswerIsCorrect: AirportUiMessage()

    object AnswerIsNotCorrect: AirportUiMessage()
}