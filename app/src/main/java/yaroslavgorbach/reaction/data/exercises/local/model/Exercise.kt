package yaroslavgorbach.reaction.data.exercises.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import yaroslavgorbach.reaction.feature.exercise.common.mapper.ExerciseNameToDescriptionResMapper
import yaroslavgorbach.reaction.feature.exercise.common.mapper.ExerciseNameToIconResMapper
import yaroslavgorbach.reaction.feature.exercise.common.mapper.ExerciseNameToInstructionResMapper

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = false)
    val name: ExerciseName,
    val numberOfWins: Int = 0,
    val isAvailable: Boolean = false,
) {
    companion object {
        const val NEED_WINS_TO_OPEN_NEXT_EXERCISE = 5

        val Empty = Exercise(
            name = ExerciseName.NO_NAME,
            numberOfWins = 0,
            isAvailable = true
        )
        val Test = Exercise(
            name = ExerciseName.ROTATION,
            numberOfWins = 3,
            isAvailable = true
        )
    }

    val descriptionRes: Int
        get() = ExerciseNameToDescriptionResMapper.map(name)

    val instructionRes: Int
        get() = ExerciseNameToInstructionResMapper.map(name)

    val iconRes: Int
        get() = ExerciseNameToIconResMapper.map(name)

    val isNextExerciseAvailable
        get() = numberOfWins >= NEED_WINS_TO_OPEN_NEXT_EXERCISE

    val nextAvailabilityProgress: Float
        get() = numberOfWins.toFloat() / NEED_WINS_TO_OPEN_NEXT_EXERCISE.toFloat()
}
