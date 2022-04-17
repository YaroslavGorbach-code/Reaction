package yaroslavgorbach.reaction.feature.exercise.common.mapper

import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName

object ExerciseNameToIconResMapper {
    fun map(exerciseName: ExerciseName): Int {
        return when (exerciseName) {
            ExerciseName.EXTRA_NUMBER -> R.drawable.ic_number
            ExerciseName.EXTRA_WORD -> R.drawable.ic_letters
            ExerciseName.FACE_CONTROL -> R.drawable.ic_face
            ExerciseName.COMPLEX_SORT -> R.drawable.ic_category
            ExerciseName.NO_NAME -> 0
            ExerciseName.STROOP -> R.drawable.ic_paint
            ExerciseName.GEO_SWITCHING -> R.drawable.ic_square
            ExerciseName.NUMBERS_AND_LETTERS -> R.drawable.ic_number_letter
            ExerciseName.AIRPORT -> R.drawable.ic_planes
            ExerciseName.ROTATION -> R.drawable.ic_rotation
        }
    }
}