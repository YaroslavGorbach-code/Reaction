package yaroslavgorbach.reaction.feature.exercise.airport.model

import yaroslavgorbach.reaction.data.exercise.airport.model.Direction

sealed class AirportActions {
    class Chose(val direction: Direction) : AirportActions()
    object Back : AirportActions()
    object Repeat : AirportActions()
    object FinishExercise : AirportActions()
}