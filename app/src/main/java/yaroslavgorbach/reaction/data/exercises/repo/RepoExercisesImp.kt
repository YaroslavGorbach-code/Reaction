package yaroslavgorbach.reaction.data.exercises.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercises.local.dao.ExerciseDao
import yaroslavgorbach.reaction.data.exercises.local.model.Exercise
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName

class RepoExercisesImp(private val local: ExerciseDao) : RepoExercises {

    private val exercises: List<Exercise>
        get() = listOf(
            Exercise(name = ExerciseName.EXTRA_NUMBER, isAvailable = true),
            Exercise(name = ExerciseName.COMPLEX_SORT),
            Exercise(name = ExerciseName.STROOP),
            Exercise(name = ExerciseName.EXTRA_WORD),
            Exercise(name = ExerciseName.GEO_SWITCHING),
            Exercise(name = ExerciseName.FACE_CONTROL),
            Exercise(name = ExerciseName.NUMBERS_AND_LETTERS),
            Exercise(name = ExerciseName.AIRPORT),
            Exercise(name = ExerciseName.ROTATION)
        )

    override fun observe(): Flow<List<Exercise>> {
        return local.observe().onEach {
            if (it.isEmpty()) {
                insert(exercises)
            }
        }
    }

    override suspend fun get(exerciseName: ExerciseName): Exercise {
       return local.get(exerciseName)
    }

    override suspend fun insert(exercise: Exercise) {
        insert(listOf(exercise))
    }

    override suspend fun insert(exercises: List<Exercise>) {
        local.insert(exercises)
    }

    override suspend fun update(exercise: Exercise) {
        local.update(exercise)
    }

}