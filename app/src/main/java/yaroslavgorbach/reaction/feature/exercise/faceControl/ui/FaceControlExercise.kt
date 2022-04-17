package yaroslavgorbach.reaction.feature.exercise.faceControl.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.reaction.data.exercise.faceControl.model.FacePack
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme
import yaroslavgorbach.reaction.feature.exercise.common.mapper.ExerciseNameToInstructionResMapper
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseResult
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseTopBar
import yaroslavgorbach.reaction.feature.exercise.extraWord.model.ExtraWordUiMessage
import yaroslavgorbach.reaction.feature.exercise.faceControl.model.FaceControlActions
import yaroslavgorbach.reaction.feature.exercise.faceControl.model.FaceControlUiMessage
import yaroslavgorbach.reaction.feature.exercise.faceControl.model.FaceControlViewState
import yaroslavgorbach.reaction.feature.exercise.faceControl.presentation.FaceControlViewModel
import yaroslavgorbach.reaction.utill.TimerCountDown

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun FaceControl(
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    FaceControl(
        viewModel = hiltViewModel(),
        onBackClick = onBackClick,
        onRepeatExerciseClick = onRepeatExerciseClick
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun FaceControl(
    viewModel: FaceControlViewModel,
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    val viewState = viewModel.state.collectAsState()

    FaceControl(
        state = viewState.value,
        onMessageShown = viewModel::clearMessage,
        actioner = { action ->
            when (action) {
                is FaceControlActions.Back -> onBackClick()
                is FaceControlActions.Repeat -> onRepeatExerciseClick()
                else -> viewModel.submitAction(action)
            }
        }
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun FaceControl(
    state: FaceControlViewState,
    actioner: (FaceControlActions) -> Unit,
    onMessageShown: (id: Long) -> Unit,
) {
    if (state.finishExerciseState.isFinished) {
        ExerciseResult(
            finishExerciseState = state.finishExerciseState,
            onBackClick = { actioner(FaceControlActions.Back) },
            onRepeatExercise = { actioner(FaceControlActions.Repeat) }
        )
    } else {
        Column {
            when (state.timerState) {
                TimerCountDown.TimerState.Finish -> {
                    actioner(FaceControlActions.FinishExercise)
                }
                is TimerCountDown.TimerState.Tick -> {
                    ExerciseTopBar(
                        instruction = stringResource(
                            id = ExerciseNameToInstructionResMapper.map(
                                exerciseName = ExerciseName.FACE_CONTROL
                            )
                        ),
                        timeProgress = state.timerState.timeUntilFinishedProgress,
                        onBack = { actioner(FaceControlActions.Back) }
                    )
                }
            }

            Box(Modifier.fillMaxSize()) {

                state.message?.let { message ->
                    when (message.message) {
                        FaceControlUiMessage.AnswerIsCorrect -> {
                            Icon(
                                Icons.Default.Circle,
                                contentDescription = "",
                                tint = Color.Green,
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(top = 4.dp)
                                    .fillMaxWidth()
                            )
                        }

                        FaceControlUiMessage.AnswerIsNotCorrect -> {
                            Icon(
                                Icons.Default.Circle,
                                contentDescription = "",
                                tint = Color.Red,
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(top = 4.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                    onMessageShown(message.id)
                }

                LazyVerticalGrid(
                    contentPadding = PaddingValues(8.dp),
                    cells = GridCells.Adaptive(60.dp),
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    state.facePacks.firstOrNull()?.faces?.let { faces ->
                        items(faces) { face ->
                            FaceItem(
                                face = face,
                                onFaceClick = { actioner(FaceControlActions.FaceClick(face)) })
                        }
                    }
                }
            }
        }

    }
}


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun ExercisesPreview() {
    ReactionTheme {
        FaceControl(state = FaceControlViewState(facePacks = listOf(FacePack.Test)), actioner = {}, onMessageShown = {})
    }
}