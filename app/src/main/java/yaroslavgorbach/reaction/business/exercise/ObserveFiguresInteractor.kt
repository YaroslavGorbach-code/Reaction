package yaroslavgorbach.reaction.business.exercise

import yaroslavgorbach.reaction.data.exercise.repo.RepoExercise
import javax.inject.Inject

class ObserveFiguresInteractor @Inject constructor(val repoExercise: RepoExercise) {
    operator fun invoke() = repoExercise.observeGeoFigures()
}