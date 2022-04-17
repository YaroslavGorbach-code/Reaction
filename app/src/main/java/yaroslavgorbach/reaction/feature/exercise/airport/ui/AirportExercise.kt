package yaroslavgorbach.reaction.feature.exercise.airport.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.reaction.data.exercise.airport.model.Direction
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme
import yaroslavgorbach.reaction.feature.exercise.airport.model.AirportActions
import yaroslavgorbach.reaction.feature.exercise.airport.model.AirportUiMessage
import yaroslavgorbach.reaction.feature.exercise.airport.model.AirportViewState
import yaroslavgorbach.reaction.feature.exercise.airport.presentation.AirportViewModel
import yaroslavgorbach.reaction.feature.exercise.common.mapper.ExerciseNameToInstructionResMapper
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseResult
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseTopBar
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model.ComplexSortUiMessage
import yaroslavgorbach.reaction.utill.TimerCountDown

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun AirportExercise(
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    AirportExercise(
        viewModel = hiltViewModel(),
        onBackClick = onBackClick,
        onRepeatExerciseClick = onRepeatExerciseClick
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun AirportExercise(
    viewModel: AirportViewModel,
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    val viewState = viewModel.state.collectAsState()

    AirportExercise(
        state = viewState.value,
        onMessageShown = viewModel::clearMessage,
        actioner = { action ->
            when (action) {
                is AirportActions.Back -> onBackClick()
                is AirportActions.Repeat -> onRepeatExerciseClick()
                else -> viewModel.submitAction(action)
            }
        }
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun AirportExercise(
    state: AirportViewState,
    actioner: (AirportActions) -> Unit,
    onMessageShown: (id: Long) -> Unit,
) {
    if (state.finishExerciseState.isFinished) {
        ExerciseResult(
            finishExerciseState = state.finishExerciseState,
            onBackClick = { actioner(AirportActions.Back) },
            onRepeatExercise = { actioner(AirportActions.Repeat) }
        )
    } else {
        Box(Modifier.fillMaxSize()) {
            when (state.timerState) {
                TimerCountDown.TimerState.Finish -> {
                    actioner(AirportActions.FinishExercise)
                }
                is TimerCountDown.TimerState.Tick -> {
                    ExerciseTopBar(
                        modifier = Modifier.align(Alignment.TopCenter),
                        instruction = stringResource(
                            id = ExerciseNameToInstructionResMapper.map(
                                exerciseName = ExerciseName.AIRPORT,
                                airportTaskVariant = state.plane.taskVariant
                            )
                        ),
                        timeProgress = state.timerState.timeUntilFinishedProgress,
                        onBack = { actioner(AirportActions.Back) },
                        content = {
                            state.message?.let { message ->
                                when (message.message) {
                                    AirportUiMessage.AnswerIsCorrect -> {
                                        Icon(
                                            Icons.Default.Circle,
                                            contentDescription = "",
                                            tint = Color.Green,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .padding(top = 4.dp)
                                                .fillMaxWidth()
                                        )
                                    }

                                    AirportUiMessage.AnswerIsNotCorrect -> {
                                        Icon(
                                            Icons.Default.Circle,
                                            contentDescription = "",
                                            tint = Color.Red,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
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

            Icon(
                imageVector = state.plane.icon,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .rotate(state.plane.direction.degree)
                    .background(color = MaterialTheme.colors.onSurface, shape = MaterialTheme.shapes.large)
                    .padding(16.dp)
                    .size(120.dp),
                tint = state.plane.color,
            )

            BottomButtons { actioner(AirportActions.Chose(it)) }
        }
    }
}

@Composable
private fun BoxScope.BottomButtons(onClick: (Direction) -> Unit) {
    Box(
        modifier = Modifier.Companion
            .align(Alignment.BottomCenter)
            .size(210.dp)
            .padding(bottom = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.North,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .background(
                    color = MaterialTheme.colors.onSurface,
                    shape = MaterialTheme.shapes.medium
                )
                .clickable { onClick(Direction.NORTH) }
                .padding(8.dp)
                .size(50.dp)
        )

        Icon(
            imageVector = Icons.Default.South,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(
                    color = MaterialTheme.colors.onSurface,
                    shape = MaterialTheme.shapes.medium
                )
                .clickable { onClick(Direction.SOUTH) }
                .padding(8.dp)
                .size(50.dp)
        )

        Icon(
            imageVector = Icons.Default.West,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .background(
                    color = MaterialTheme.colors.onSurface,
                    shape = MaterialTheme.shapes.medium
                )
                .clickable { onClick(Direction.WEST) }
                .padding(8.dp)
                .size(50.dp)
        )

        Icon(
            imageVector = Icons.Default.East,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(
                    color = MaterialTheme.colors.onSurface,
                    shape = MaterialTheme.shapes.medium
                )
                .clickable { onClick(Direction.EAST) }
                .padding(8.dp)
                .size(50.dp)
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun ExercisesPreview() {
    ReactionTheme {
        AirportExercise(state = AirportViewState.Test, actioner = {}, onMessageShown = {})
    }
}