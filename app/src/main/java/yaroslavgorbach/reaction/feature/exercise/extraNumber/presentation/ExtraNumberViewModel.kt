package yaroslavgorbach.reaction.feature.exercise.extraNumber.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import yaroslavgorbach.reaction.business.exercise.ObserveExtraNumbersInteractor
import yaroslavgorbach.reaction.business.exercises.GetExerciseInteractor
import yaroslavgorbach.reaction.business.exercises.UpdateExerciseInteractor
import yaroslavgorbach.reaction.data.exercise.extraNumber.local.model.Number
import yaroslavgorbach.reaction.data.exercise.extraNumber.local.model.NumberPack
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.base.BaseExerciseViewModel
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState
import yaroslavgorbach.reaction.feature.exercise.extraNumber.model.ExtraNumberActions
import yaroslavgorbach.reaction.feature.exercise.extraNumber.model.ExtraNumberUiMessage
import yaroslavgorbach.reaction.feature.exercise.extraNumber.model.ExtraNumberViewState
import yaroslavgorbach.reaction.utill.UiMessage
import yaroslavgorbach.reaction.utill.UiMessageManager
import yaroslavgorbach.reaction.utill.combine
import javax.inject.Inject

@HiltViewModel
class ExtraNumberViewModel @Inject constructor(
    observeExtraNumbersInteractor: ObserveExtraNumbersInteractor,
    private val updateExerciseInteractor: UpdateExerciseInteractor,
    getExerciseInteractor: GetExerciseInteractor
) : BaseExerciseViewModel(
    exerciseName = ExerciseName.EXTRA_NUMBER,
    getExerciseInteractor = getExerciseInteractor
) {

    private val pendingActions = MutableSharedFlow<ExtraNumberActions>()

    private val numberPacks: MutableStateFlow<List<NumberPack>> = MutableStateFlow(emptyList())

    override val uiMessageManager: UiMessageManager<ExtraNumberUiMessage> = UiMessageManager()

    var state: StateFlow<ExtraNumberViewState> = combine(
        numberPacks,
        timerCountDown.state,
        pointsCorrect,
        pointsInCorrect,
        isExerciseFinished,
        uiMessageManager.message,
    ) { numberPacks, timerState, pointsCorrect, pointsIncorrect, isFinish, message ->
        ExtraNumberViewState(
            numberPacks = numberPacks,
            timerState = timerState,
            finishExerciseState = FinishExerciseState(
                name = exerciseName,
                isFinished = isFinish,
                pointsCorrect = pointsCorrect,
                pointsIncorrect = pointsIncorrect
            ),
            message = message
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = ExtraNumberViewState.Empty
    )

    init {
        timerCountDown.start()

        viewModelScope.launch {
            observeExtraNumbersInteractor()
                .flowOn(Dispatchers.IO)
                .collect(numberPacks::emit)

            pendingActions.collect { action ->
                when (action) {
                    is ExtraNumberActions.NumberClick -> onNumberClick(action.number)
                    is ExtraNumberActions.FinishExercise -> finishExercise()
                    else -> error("$action is not handled")
                }
            }
        }
    }

    override suspend fun finishExercise() {
        super.finishExercise()
        updateExercise()
    }

    private suspend fun updateExercise() {
        if (state.value.finishExerciseState.isWin) {
            exercise?.let { updateExerciseInteractor(exercise = it.copy(numberOfWins = it.numberOfWins + 1)) }
        }
    }

    private fun onNumberClick(number: Number) {
        viewModelScope.launch {
            if (number.isExtra) {
                pointsCorrect.emit(pointsCorrect.value + 1)
                uiMessageManager.emitMessage(UiMessage(ExtraNumberUiMessage.AnswerIsCorrect))
            } else {
                pointsInCorrect.emit(pointsInCorrect.value + 1)
                uiMessageManager.emitMessage(UiMessage(ExtraNumberUiMessage.AnswerIsNotCorrect))
            }

            numberPacks.emit(numberPacks.value.drop(1))
        }
    }

    fun submitAction(action: ExtraNumberActions) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }
}
