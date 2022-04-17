package yaroslavgorbach.reaction.feature.exercise.stroop.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercise.stroop.model.WordColorVariant
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme
import yaroslavgorbach.reaction.feature.exercise.common.mapper.ExerciseNameToInstructionResMapper
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseResult
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseTopBar
import yaroslavgorbach.reaction.feature.exercise.rotation.model.RotationUiMessage
import yaroslavgorbach.reaction.feature.exercise.stroop.model.StroopActions
import yaroslavgorbach.reaction.feature.exercise.stroop.model.StroopUiMessage
import yaroslavgorbach.reaction.feature.exercise.stroop.model.StroopViewState
import yaroslavgorbach.reaction.feature.exercise.stroop.presentation.StroopViewModel
import yaroslavgorbach.reaction.utill.TimerCountDown


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@InternalCoroutinesApi
@Composable
fun StroopExercise(
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    StroopExercise(
        viewModel = hiltViewModel(),
        onBackClick = onBackClick,
        onRepeatExerciseClick = onRepeatExerciseClick
    )
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun StroopExercise(
    viewModel: StroopViewModel,
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    val viewState = viewModel.state.collectAsState()

    StroopExercise(
        state = viewState.value,
        onMessageShown = viewModel::clearMessage,
        actioner = { action ->
            when (action) {
                is StroopActions.Back -> onBackClick()
                is StroopActions.Repeat -> onRepeatExerciseClick()
                else -> viewModel.submitAction(action)
            }
        }
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun StroopExercise(
    state: StroopViewState,
    actioner: (StroopActions) -> Unit,
    onMessageShown: (id: Long) -> Unit,
    ) {
    if (state.finishExerciseState.isFinished) {
        ExerciseResult(
            finishExerciseState = state.finishExerciseState,
            onBackClick = { actioner(StroopActions.Back) },
            onRepeatExercise = { actioner(StroopActions.Repeat) }
        )
    } else {
        Box(Modifier.fillMaxSize()) {
            when (state.timerState) {
                TimerCountDown.TimerState.Finish -> {
                    actioner(StroopActions.FinishExercise)
                }
                is TimerCountDown.TimerState.Tick -> {
                    ExerciseTopBar(
                        modifier = Modifier.align(Alignment.TopCenter),
                        instruction = stringResource(
                            id = ExerciseNameToInstructionResMapper.map(
                                exerciseName = ExerciseName.STROOP
                            )
                        ),
                        timeProgress = state.timerState.timeUntilFinishedProgress,
                        onBack = { actioner(StroopActions.Back) },
                        content = {
                            state.message?.let { message ->
                                when (message.message) {
                                    StroopUiMessage.AnswerIsCorrect -> {
                                        Icon(
                                            Icons.Default.Circle,
                                            contentDescription = "",
                                            tint = Color.Green,
                                            modifier = Modifier
                                                .align(CenterHorizontally)
                                                .padding(top = 4.dp)
                                                .fillMaxWidth()
                                        )
                                    }

                                    StroopUiMessage.AnswerIsNotCorrect -> {
                                        Icon(
                                            Icons.Default.Circle,
                                            contentDescription = "",
                                            tint = Color.Red,
                                            modifier = Modifier
                                                .align(CenterHorizontally)
                                                .padding(top = 4.dp)
                                                .fillMaxWidth()
                                        )
                                    }
                                }
                                onMessageShown(message.id)
                            }
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(
                        color = MaterialTheme.colors.onSurface,
                        shape = MaterialTheme.shapes.large
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = state.word.text,
                    color = state.word.color.color,
                    fontSize = 40.sp,
                    style = MaterialTheme.typography.caption
                )
            }


            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .wrapContentSize()
            ) {
                Button(
                    shape = RoundedCornerShape(30),
                    onClick = { actioner(StroopActions.OnChose(WordColorVariant.RED)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                ) {
                    Text(text = stringResource(id = R.string.red), fontSize = 24.sp)
                }

                Button(modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
                    .weight(0.5f),
                    shape = RoundedCornerShape(30),
                    onClick = { actioner(StroopActions.OnChose(WordColorVariant.GREEN)) }) {
                    Text(text = stringResource(id = R.string.green), fontSize = 24.sp)
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
        StroopExercise(state = StroopViewState.Test, actioner = {}, onMessageShown = {})
    }
}