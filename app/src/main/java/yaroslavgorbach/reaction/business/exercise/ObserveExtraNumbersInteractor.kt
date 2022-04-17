package yaroslavgorbach.reaction.business.exercise

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.reaction.data.exercise.extraNumber.local.model.NumberPack
import yaroslavgorbach.reaction.data.exercise.repo.RepoExercise
import javax.inject.Inject

class ObserveExtraNumbersInteractor @Inject constructor(private val repoExercise: RepoExercise) {
    operator fun invoke(): Flow<List<NumberPack>> {
        return repoExercise.observeExtraNumber()
    }
}