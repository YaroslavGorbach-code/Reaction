package yaroslavgorbach.reaction.feature.exercise.numbersAndLetters.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import yaroslavgorbach.reaction.business.exercise.ObserveNumbersAndLettersInteractor
import yaroslavgorbach.reaction.business.exercises.GetExerciseInteractor
import yaroslavgorbach.reaction.business.exercises.UpdateExerciseInteractor
import yaroslavgorbach.reaction.data.exercise.numbersLetters.model.NumberAndLetter
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.base.BaseExerciseViewModel
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState
import yaroslavgorbach.reaction.feature.exercise.common.model.YesNoChoseVariations
import yaroslavgorbach.reaction.feature.exercise.numbersAndLetters.model.NumbersAndLettersActions
import yaroslavgorbach.reaction.feature.exercise.numbersAndLetters.model.NumbersAndLettersUiMessage
import yaroslavgorbach.reaction.feature.exercise.numbersAndLetters.model.NumbersAndLettersViewState
import yaroslavgorbach.reaction.utill.UiMessage
import yaroslavgorbach.reaction.utill.UiMessageManager
import yaroslavgorbach.reaction.utill.combine
import yaroslavgorbach.reaction.utill.firstOr
import javax.inject.Inject

@HiltViewModel
class NumbersAndLettersViewModel @Inject constructor(
    observeNumbersAndLettersInteractor: ObserveNumbersAndLettersInteractor,
    getExerciseInteractor: GetExerciseInteractor,
    private val updateExerciseInteractor: UpdateExerciseInteractor,
) : BaseExerciseViewModel(exerciseName = ExerciseName.NUMBERS_AND_LETTERS, getExerciseInteractor) {

    private val pendingActions = MutableSharedFlow<NumbersAndLettersActions>()

    private val items: MutableStateFlow<List<NumberAndLetter>> = MutableStateFlow(emptyList())

    override val uiMessageManager: UiMessageManager<NumbersAndLettersUiMessage> = UiMessageManager()

    val state: StateFlow<NumbersAndLettersViewState> = combine(
        items,
        timerCountDown.state,
        pointsCorrect,
        pointsInCorrect,
        isExerciseFinished,
        uiMessageManager.message
    ) { numbersAndLetters, timerState, pointsCorrect, pointsIncorrect, isExerciseFinished, message ->
        NumbersAndLettersViewState(
            numberAndLetter = numbersAndLetters.firstOr(NumberAndLetter.Test),
            timerState = timerState,
            finishExerciseState = FinishExerciseState(
                name = exerciseName,
                isFinished = isExerciseFinished,
                pointsCorrect = pointsCorrect,
                pointsIncorrect = pointsIncorrect
            ),
            message = message
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = NumbersAndLettersViewState.Test
    )

    init {
        viewModelScope.launch {
            observeNumbersAndLettersInteractor()
                .flowOn(Dispatchers.IO)
                .collect(items::emit)

            pendingActions.collect { action ->
                when (action) {
                    is NumbersAndLettersActions.Chose -> onChose(action.chose)
                    is NumbersAndLettersActions.FinishExercise -> finishExercise()
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

    private fun onChose(variant: YesNoChoseVariations) {
        viewModelScope.launch {
            val currentItem = items.first().first()

            currentItem.checkAnswer(variant) { isCorrect ->
                if (isCorrect) {
                    pointsCorrect.emit(pointsCorrect.value + 1)
                    uiMessageManager.emitMessage(UiMessage(NumbersAndLettersUiMessage.AnswerIsCorrect))
                } else {
                    pointsInCorrect.emit(pointsInCorrect.value + 1)
                    uiMessageManager.emitMessage(UiMessage(NumbersAndLettersUiMessage.AnswerIsNotCorrect))
                }
            }

            items.emit(items.value.drop(1))
        }
    }

    fun submitAction(action: NumbersAndLettersActions) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }
}
