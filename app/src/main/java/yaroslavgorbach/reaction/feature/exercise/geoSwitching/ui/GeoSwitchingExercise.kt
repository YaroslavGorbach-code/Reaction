package yaroslavgorbach.reaction.feature.exercise.geoSwitching.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercise.geoSwitching.model.TaskVariant
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme
import yaroslavgorbach.reaction.feature.exercise.common.mapper.ExerciseNameToInstructionResMapper
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseResult
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseTopBar
import yaroslavgorbach.reaction.feature.exercise.common.ui.YesNoBottomButtons
import yaroslavgorbach.reaction.feature.exercise.faceControl.model.FaceControlUiMessage
import yaroslavgorbach.reaction.feature.exercise.geoSwitching.model.GeoSwitchingActions
import yaroslavgorbach.reaction.feature.exercise.geoSwitching.model.GeoSwitchingUiMessage
import yaroslavgorbach.reaction.feature.exercise.geoSwitching.model.GeoSwitchingViewState
import yaroslavgorbach.reaction.feature.exercise.geoSwitching.presentation.GeoSwitchingViewModel
import yaroslavgorbach.reaction.utill.TimerCountDown


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@InternalCoroutinesApi
@Composable
fun GeoSwitchingExercise(
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    GeoSwitchingExercise(
        viewModel = hiltViewModel(),
        onBackClick = onBackClick,
        onRepeatExerciseClick = onRepeatExerciseClick
    )
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun GeoSwitchingExercise(
    viewModel: GeoSwitchingViewModel,
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    val viewState = viewModel.state.collectAsState()

    GeoSwitchingExercise(
        state = viewState.value,
        onMessageShown = viewModel::clearMessage,
        actioner = { action ->
            when (action) {
                is GeoSwitchingActions.Back -> onBackClick()
                is GeoSwitchingActions.Repeat -> onRepeatExerciseClick()
                else -> viewModel.submitAction(action)
            }
        }
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun GeoSwitchingExercise(
    state: GeoSwitchingViewState,
    actioner: (GeoSwitchingActions) -> Unit,
    onMessageShown: (id: Long) -> Unit,
) {
    if (state.finishExerciseState.isFinished) {
        ExerciseResult(
            finishExerciseState = state.finishExerciseState,
            onBackClick = { actioner(GeoSwitchingActions.Back) },
            onRepeatExercise = { actioner(GeoSwitchingActions.Repeat) }
        )
    } else {
        Box(Modifier.fillMaxSize()) {
            when (state.timerState) {
                TimerCountDown.TimerState.Finish -> {
                    actioner(GeoSwitchingActions.FinishExercise)
                }
                is TimerCountDown.TimerState.Tick -> {
                    ExerciseTopBar(
                        modifier = Modifier.align(Alignment.TopCenter),
                        instruction = stringResource(
                            id = ExerciseNameToInstructionResMapper.map(
                                exerciseName = ExerciseName.GEO_SWITCHING
                            )
                        ),
                        timeProgress = state.timerState.timeUntilFinishedProgress,
                        onBack = { actioner(GeoSwitchingActions.Back) },
                        content = {
                            state.message?.let { message ->
                                when (message.message) {
                                    GeoSwitchingUiMessage.AnswerIsCorrect -> {
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

                                    GeoSwitchingUiMessage.AnswerIsNotCorrect -> {
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

            CroiseVariants(state)
            YesNoBottomButtons(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .wrapContentSize()
            ) { variant -> actioner(GeoSwitchingActions.Chose(variant)) }
        }
    }
}

@Composable
private fun BoxScope.CroiseVariants(state: GeoSwitchingViewState) {
    Row(
        modifier = Modifier.Companion
            .align(Alignment.Center)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .weight(0.5f)
                .padding(end = 8.dp)
                .background(
                    color = MaterialTheme.colors.onSurface,
                    shape = MaterialTheme.shapes.large
                )
                .padding(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.is_square),
                fontSize = 20.sp,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            if (state.figure.taskVariant == TaskVariant.IS_SQUARE) {
                Icon(
                    imageVector = state.figure.figure.icon,
                    contentDescription = null,
                    tint = state.figure.color,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxSize()
                )
            }
        }

        Column(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .weight(0.5f)
                .padding(start = 4.dp)
                .background(
                    color = MaterialTheme.colors.onSurface,
                    shape = MaterialTheme.shapes.large
                )
                .padding(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.is_blue),
                fontSize = 20.sp,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            if (state.figure.taskVariant == TaskVariant.IS_BLUE) {
                Icon(
                    imageVector = state.figure.figure.icon,
                    contentDescription = null,
                    tint = state.figure.color,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxSize()
                )
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
        GeoSwitchingExercise(state = GeoSwitchingViewState.Test, actioner = {}, onMessageShown = {})
    }
}