package yaroslavgorbach.reaction.business.exercise

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.reaction.data.exercise.numbersLetters.model.NumberAndLetter
import yaroslavgorbach.reaction.data.exercise.repo.RepoExercise
import javax.inject.Inject

class ObserveNumbersAndLettersInteractor @Inject constructor(private val repoExercise: RepoExercise) {
    operator fun invoke(): Flow<List<NumberAndLetter>> {
        return repoExercise.observeNumbersAnLetters()
    }
}