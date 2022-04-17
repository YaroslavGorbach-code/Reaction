package yaroslavgorbach.reaction.feature.exercise.common.mapper

import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName

object ExerciseNameToDescriptionResMapper {
    fun map(exerciseName: ExerciseName): Int {
        return when (exerciseName) {
            ExerciseName.EXTRA_NUMBER -> R.string.description_extra_number
            ExerciseName.EXTRA_WORD -> R.string.description_extra_word
            ExerciseName.FACE_CONTROL -> R.string.description_face_control
            ExerciseName.COMPLEX_SORT -> R.string.description_complex_sort
            ExerciseName.NO_NAME -> 0
            ExerciseName.STROOP -> R.string.description_stroop
            ExerciseName.GEO_SWITCHING -> R.string.description_geo_switching
            ExerciseName.NUMBERS_AND_LETTERS -> R.string.description_numbers_and_letters
            ExerciseName.AIRPORT -> R.string.description_airport
            ExerciseName.ROTATION -> R.string.description_rotation
        }
    }
}