package yaroslavgorbach.reaction.data.exercise.airport.factory

import androidx.compose.ui.graphics.Color
import yaroslavgorbach.reaction.data.exercise.airport.model.AirportTaskVariant
import yaroslavgorbach.reaction.data.exercise.airport.model.Direction
import yaroslavgorbach.reaction.data.exercise.airport.model.Plane

@ExperimentalStdlibApi
class AirportPlainsFactory {
    private val taskVariants = AirportTaskVariant.values().toList()
    private val directions = Direction.values().toList()

    fun create(): List<Plane> {
        return buildList {
            repeat(1000) {
                val taskVariant = taskVariants.random()
                val color = if (taskVariant == AirportTaskVariant.FLIES_FORWARD) {
                    Color.Blue
                } else {
                    Color.Red
                }

                add(
                    Plane(
                        taskVariant = taskVariant,
                        direction = directions.random(),
                        color = color
                    )
                )
            }
        }
    }
}