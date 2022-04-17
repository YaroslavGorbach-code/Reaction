package yaroslavgorbach.reaction.business.exercises

import kotlinx.coroutines.flow.first
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.data.exercises.repo.RepoExercises
import javax.inject.Inject

class MakeExerciseAvailableInteractor @Inject constructor(
    private val repoExercises: RepoExercises,
    private val observeExercisesInteractor: ObserveExercisesInteractor
) {

    suspend operator fun invoke(exerciseName: ExerciseName) {
        val exercise = observeExercisesInteractor().first().first { it.name == exerciseName }

        repoExercises.update(exercise.copy(isAvailable = true))
    }
}