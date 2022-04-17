package yaroslavgorbach.reaction.feature.exercise.cpmplexSort.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import yaroslavgorbach.reaction.business.exercise.ObserveComplexSortItemsInteractor
import yaroslavgorbach.reaction.business.exercises.GetExerciseInteractor
import yaroslavgorbach.reaction.business.exercises.UpdateExerciseInteractor
import yaroslavgorbach.reaction.data.exercise.complexSort.model.ComplexSortItem
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.base.BaseExerciseViewModel
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model.ComplexSortUiMessage
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model.ComplexSortActions
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model.ComplexSortViewState
import yaroslavgorbach.reaction.utill.UiMessage
import yaroslavgorbach.reaction.utill.UiMessageManager
import yaroslavgorbach.reaction.utill.combine
import javax.inject.Inject

@HiltViewModel
class ComplexSortViewModel @Inject constructor(
    observeComplexSortItemPacksItemsInteractor: ObserveComplexSortItemsInteractor,
    getExerciseInteractor: GetExerciseInteractor,
    private val updateExerciseInteractor: UpdateExerciseInteractor
) : BaseExerciseViewModel(
    exerciseName = ExerciseName.COMPLEX_SORT,
    getExerciseInteractor = getExerciseInteractor
) {
    private val pendingActions = MutableSharedFlow<ComplexSortActions>()

    private val items: MutableStateFlow<List<ComplexSortItem>> = MutableStateFlow(emptyList())

    override val uiMessageManager = UiMessageManager<ComplexSortUiMessage>()

    val state: StateFlow<ComplexSortViewState> = combine(
        items,
        timerCountDown.state,
        pointsCorrect,
        pointsInCorrect,
        isExerciseFinished,
        uiMessageManager.message,
    ) { itemPacks, timerState, pointsCorrect, pointsIncorrect, isExerciseFinished, message ->
        ComplexSortViewState(
            items = itemPacks,
            timerState = timerState,
            finishExerciseState = FinishExerciseState(
                name = exerciseName,
                isFinished = isExerciseFinished,
                pointsCorrect = pointsCorrect,
                pointsIncorrect = pointsIncorrect
            ),
            uiMessage = message
        )

    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = ComplexSortViewState.Empty
    )

    init {
        viewModelScope.launch {
            observeComplexSortItemPacksItemsInteractor()
                .flowOn(Dispatchers.IO)
                .collect(items::emit)

            pendingActions.collect { action ->
                when (action) {
                    is ComplexSortActions.ItemClick -> onItemClick(action.item)
                    is ComplexSortActions.FinishExercise -> finishExercise()
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

    private fun onItemClick(item: ComplexSortItem) {
        viewModelScope.launch {
            val currentItem = items.first().first()
            currentItem.checkAnswer(item) { isCorrect ->
                if (isCorrect) {
                    pointsCorrect.emit(pointsCorrect.value + 1)
                    uiMessageManager.emitMessage(UiMessage(ComplexSortUiMessage.AnswerIsCorrect))
                } else {
                    pointsInCorrect.emit(pointsInCorrect.value + 1)
                    uiMessageManager.emitMessage(UiMessage(ComplexSortUiMessage.AnswerIsNotCorrect))
                }
            }
            items.emit(items.value.drop(1))
        }
    }

    fun submitAction(action: ComplexSortActions) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }
}
