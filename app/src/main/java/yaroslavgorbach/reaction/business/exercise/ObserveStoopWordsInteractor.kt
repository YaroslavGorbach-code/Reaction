package yaroslavgorbach.reaction.business.exercise

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.reaction.data.exercise.repo.RepoExercise
import yaroslavgorbach.reaction.data.exercise.stroop.model.StroopWord
import javax.inject.Inject

class ObserveStoopWordsInteractor @Inject constructor(private val repoExercise: RepoExercise) {
    operator fun invoke(): Flow<List<StroopWord>> {
        return repoExercise.observeStroopWords()
    }
}