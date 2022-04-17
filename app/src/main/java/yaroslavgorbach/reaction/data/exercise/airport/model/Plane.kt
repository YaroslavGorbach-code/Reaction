package yaroslavgorbach.reaction.data.exercise.airport.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Plane(
    val icon: ImageVector = Icons.Filled.Flight,
    val color: Color,
    val taskVariant: AirportTaskVariant,
    val direction: Direction
) {
    companion object {
        val Test = Plane(
            taskVariant = AirportTaskVariant.FLIES_BACKWARD,
            direction = Direction.NORTH,
            color = Color.Blue
        )
    }

    fun checkIsResultCorrect(directionChosen: Direction): Boolean {
        return when (taskVariant) {
            AirportTaskVariant.FLIES_FORWARD -> {
                when (direction) {
                    Direction.NORTH -> directionChosen == Direction.NORTH
                    Direction.SOUTH -> directionChosen == Direction.SOUTH
                    Direction.WEST -> directionChosen == Direction.WEST
                    Direction.EAST -> directionChosen == Direction.EAST
                }
            }
            AirportTaskVariant.FLIES_BACKWARD -> {
                when (direction) {
                    Direction.NORTH -> directionChosen == Direction.SOUTH
                    Direction.SOUTH -> directionChosen == Direction.NORTH
                    Direction.WEST -> directionChosen == Direction.EAST
                    Direction.EAST -> directionChosen == Direction.WEST
                }
            }
        }
    }
}