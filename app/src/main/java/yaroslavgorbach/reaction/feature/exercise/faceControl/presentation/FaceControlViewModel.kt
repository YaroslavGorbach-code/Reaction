package yaroslavgorbach.reaction.feature.exercise.faceControl.presentation

import androidx.lifecycle.viewModelScope
import yaroslavgorbach.reaction.utill.combine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import yaroslavgorbach.reaction.business.exercise.ObserveFaceControlInteractor
import yaroslavgorbach.reaction.business.exercises.GetExerciseInteractor
import yaroslavgorbach.reaction.business.exercises.UpdateExerciseInteractor
import yaroslavgorbach.reaction.data.exercise.faceControl.model.Face
import yaroslavgorbach.reaction.data.exercise.faceControl.model.FacePack
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.base.BaseExerciseViewModel
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState
import yaroslavgorbach.reaction.feature.exercise.faceControl.model.FaceControlActions
import yaroslavgorbach.reaction.feature.exercise.faceControl.model.FaceControlUiMessage
import yaroslavgorbach.reaction.feature.exercise.faceControl.model.FaceControlViewState
import yaroslavgorbach.reaction.utill.UiMessage
import yaroslavgorbach.reaction.utill.UiMessageManager
import javax.inject.Inject

@HiltViewModel
class FaceControlViewModel @Inject constructor(
    observeFaceControlInteractor: ObserveFaceControlInteractor,
    getExerciseInteractor: GetExerciseInteractor,
    private val updateExerciseInteractor: UpdateExerciseInteractor,
) : BaseExerciseViewModel(exerciseName = ExerciseName.FACE_CONTROL, getExerciseInteractor) {

    private val pendingActions = MutableSharedFlow<FaceControlActions>()

    private val facePacks: MutableStateFlow<List<FacePack>> = MutableStateFlow(emptyList())

    override val uiMessageManager: UiMessageManager<FaceControlUiMessage> = UiMessageManager()

    var state: StateFlow<FaceControlViewState> = combine(
        facePacks,
        timerCountDown.state,
        pointsCorrect,
        pointsInCorrect,
        isExerciseFinished,
        uiMessageManager.message
    ) { facePacks, timerState, pointsCorrect, pointsIncorrect, isExerciseFinished, message ->
        FaceControlViewState(
            facePacks = facePacks,
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
        initialValue = FaceControlViewState.Empty
    )

    init {
        viewModelScope.launch {
            observeFaceControlInteractor()
                .flowOn(Dispatchers.IO)
                .collect(facePacks::emit)

            pendingActions.collect { action ->
                when (action) {
                    is FaceControlActions.FaceClick -> onFaceClick(action.face)
                    is FaceControlActions.FinishExercise -> finishExercise()
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

    private fun onFaceClick(face: Face) {
        viewModelScope.launch {
            if (face.isDissatisfied) {
                pointsCorrect.emit(pointsCorrect.value + 1)
                uiMessageManager.emitMessage(UiMessage(FaceControlUiMessage.AnswerIsCorrect))
            } else {
                pointsInCorrect.emit(pointsInCorrect.value + 1)
                uiMessageManager.emitMessage(UiMessage(FaceControlUiMessage.AnswerIsNotCorrect))
            }

            facePacks.emit(facePacks.value.drop(1))
        }
    }

    fun submitAction(action: FaceControlActions) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }
}



