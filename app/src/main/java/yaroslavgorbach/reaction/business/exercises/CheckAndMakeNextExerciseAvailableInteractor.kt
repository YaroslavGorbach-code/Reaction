package yaroslavgorbach.reaction.business.exercises

import yaroslavgorbach.reaction.data.exercises.local.model.Exercise
import yaroslavgorbach.reaction.data.exercises.repo.RepoExercises
import javax.inject.Inject

class CheckAndMakeNextExerciseAvailableInteractor @Inject constructor(
    private val repoExercises: RepoExercises,
) {

    suspend operator fun invoke(exercises: List<Exercise>) {
        val exerciseToMakeValuable = getNextAvailableExercise(exercises)

        exerciseToMakeValuable?.let {
            repoExercises.update(exerciseToMakeValuable.copy(isAvailable = true))
        }
    }

    private fun getNextAvailableExercise(exercises: List<Exercise>): Exercise? {
        val lastAvailable = exercises.lastOrNull { it.isAvailable }
        val isNextAvailable = lastAvailable?.isNextExerciseAvailable

        return if (isNextAvailable == true) {
            val indexOfNextAfterLastAvailable = exercises.indexOf(lastAvailable) + 1
            exercises.getOrNull(indexOfNextAfterLastAvailable)
        } else {
            null
        }
    }
}