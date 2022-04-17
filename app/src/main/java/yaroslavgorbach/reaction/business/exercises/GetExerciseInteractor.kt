package yaroslavgorbach.reaction.business.exercises

import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.data.exercises.repo.RepoExercises
import javax.inject.Inject

class GetExerciseInteractor @Inject constructor (private val repoExercises: RepoExercises) {
    suspend operator fun invoke(exerciseName: ExerciseName) = repoExercises.get(exerciseName)
}