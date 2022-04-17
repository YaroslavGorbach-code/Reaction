package yaroslavgorbach.reaction.data.exercises.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.reaction.data.exercises.local.model.Exercise
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName

interface RepoExercises {
    fun observe(): Flow<List<Exercise>>

    suspend fun get(exerciseName: ExerciseName): Exercise

    suspend fun insert(exercise: Exercise)

    suspend fun insert(exercises: List<Exercise>)

    suspend fun update(exercise: Exercise)
}