package yaroslavgorbach.reaction.feature.description.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import yaroslavgorbach.reaction.EXERCISE_NAME_ARG
import yaroslavgorbach.reaction.business.description.ObserveDescriptionResInteractor
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.description.model.DescriptionActions
import yaroslavgorbach.reaction.feature.description.model.DescriptionViewState
import javax.inject.Inject

@HiltViewModel
class DescriptionViewModel @Inject constructor(
    observeDescriptionInteractor: ObserveDescriptionResInteractor,
    savedState: SavedStateHandle
) : ViewModel() {

    val exerciseName: ExerciseName = savedState[EXERCISE_NAME_ARG]!!

    private val pendingActions = MutableSharedFlow<DescriptionActions>()

    val state: StateFlow<DescriptionViewState> = combine(
        observeDescriptionInteractor(exerciseName)
    ) { description ->
        DescriptionViewState(
            exerciseName = exerciseName,
            descriptionRes = description[0]
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DescriptionViewState.Empty
    )

    init {
        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                    else -> error("action is not handled")
                }
            }
        }
    }

    fun submitAction(action: DescriptionActions) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }
}