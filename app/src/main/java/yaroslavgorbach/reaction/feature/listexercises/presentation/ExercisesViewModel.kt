package yaroslavgorbach.reaction.feature.listexercises.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import yaroslavgorbach.reaction.business.listexercises.ObserveExercisesInteractor
import yaroslavgorbach.reaction.data.listexercises.repo.RepoExercises
import yaroslavgorbach.reaction.data.listexercises.repo.RepoExercisesImp
import yaroslavgorbach.reaction.feature.listexercises.model.ExercisesActions
import yaroslavgorbach.reaction.feature.listexercises.model.ExercisesViewState

class ExercisesViewModel : ViewModel() {

    private val repo: RepoExercises
        get() = RepoExercisesImp()

    private val observeExercisesInteractor: ObserveExercisesInteractor
        get() = ObserveExercisesInteractor(repo)

    private val pendingActions = MutableSharedFlow<ExercisesActions>()

    val state: StateFlow<ExercisesViewState> = combine(
        observeExercisesInteractor()
    ) { exercises ->
        ExercisesViewState(exercises = exercises[0])
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = ExercisesViewState.Empty
    )

    init {
        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                    else -> error("$action is not handled")
                }
            }
        }
    }

    fun submitAction(action: ExercisesActions) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

}



