package yaroslavgorbach.reaction.feature.exercise.common.mapper

import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercise.airport.model.AirportTaskVariant
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName

object ExerciseNameToInstructionResMapper {
    fun map(
        exerciseName: ExerciseName,
        complexSortIsSimilar: Boolean = false,
        airportTaskVariant: AirportTaskVariant = AirportTaskVariant.FLIES_FORWARD
    ): Int {
        return when (exerciseName) {
            ExerciseName.EXTRA_NUMBER -> R.string.instruction_extra_number
            ExerciseName.EXTRA_WORD -> R.string.instruction_extra_word
            ExerciseName.FACE_CONTROL -> R.string.instruction_face_control
            ExerciseName.COMPLEX_SORT -> {
                if (complexSortIsSimilar) {
                    R.string.instruction_sort_item_similar
                } else {
                    R.string.instruction_sort_item_not_similar
                }
            }
            ExerciseName.NO_NAME -> 0
            ExerciseName.STROOP -> R.string.instruction_stroop
            ExerciseName.GEO_SWITCHING -> R.string.instruction_geo_switching
            ExerciseName.NUMBERS_AND_LETTERS -> R.string.instruction_numbers_and_letters
            ExerciseName.AIRPORT -> {
                when (airportTaskVariant) {
                    AirportTaskVariant.FLIES_FORWARD -> R.string.instruction_airport_forward
                    AirportTaskVariant.FLIES_BACKWARD -> R.string.instruction_airport_backward
                }
            }
            ExerciseName.ROTATION -> R.string.instruction_rotation
        }
    }
}