package yaroslavgorbach.reaction.business.exercise

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.reaction.data.exercise.extraWord.model.WordPack
import yaroslavgorbach.reaction.data.exercise.repo.RepoExercise
import javax.inject.Inject

class ObserveExtraWordsInteractor @Inject constructor(private val repoExercise: RepoExercise) {
    operator fun invoke(): Flow<List<WordPack>> {
        return repoExercise.observeExtraWord()
    }
}