package yaroslavgorbach.reaction.business.description

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.reaction.business.listExercises.ObserveExercisesInteractor
import yaroslavgorbach.reaction.data.listExercises.local.model.ExerciseName
import javax.inject.Inject

class ObserveDescriptionResInteractor @Inject constructor(private val observeExercisesInteractor: ObserveExercisesInteractor) {
    operator fun invoke(exerciseName: ExerciseName): Flow<Int> {
        return observeExercisesInteractor.invoke().map {
            it.first { exercise -> exercise.name == exerciseName }.descriptionRes
        }
    }
}