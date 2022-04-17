package yaroslavgorbach.reaction.feature.exercise.cpmplexSort.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.reaction.data.exercise.complexSort.model.ComplexSortItem
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme
import yaroslavgorbach.reaction.feature.exercise.common.mapper.ExerciseNameToInstructionResMapper
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseResult
import yaroslavgorbach.reaction.feature.exercise.common.ui.ExerciseTopBar
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model.ComplexSortActions
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model.ComplexSortUiMessage
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model.ComplexSortViewState
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.presentation.ComplexSortViewModel
import yaroslavgorbach.reaction.utill.TimerCountDown

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ComplexSort(
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    ComplexSort(
        viewModel = hiltViewModel(),
        onBackClick = onBackClick,
        onRepeatExerciseClick = onRepeatExerciseClick
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun ComplexSort(
    viewModel: ComplexSortViewModel,
    onBackClick: () -> Unit,
    onRepeatExerciseClick: () -> Unit,
) {
    val viewState = viewModel.state.collectAsState()

    ComplexSort(
        state = viewState.value,
        onMessageShown = viewModel::clearMessage,
        actioner = { action ->
            when (action) {
                is ComplexSortActions.Back -> onBackClick()
                is ComplexSortActions.Repeat -> onRepeatExerciseClick()
                else -> viewModel.submitAction(action)
            }
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun ComplexSort(
    state: ComplexSortViewState,
    actioner: (ComplexSortActions) -> Unit,
    onMessageShown: (id: Long) -> Unit,
) {
    if (state.finishExerciseState.isFinished) {
        ExerciseResult(
            finishExerciseState = state.finishExerciseState,
            onBackClick = { actioner(ComplexSortActions.Back) },
            onRepeatExercise = { actioner(ComplexSortActions.Repeat) }
        )
    } else {
        Box(Modifier.fillMaxSize()) {
            when (state.timerState) {
                TimerCountDown.TimerState.Finish -> {
                    actioner(ComplexSortActions.FinishExercise)
                }
                is TimerCountDown.TimerState.Tick -> {
                    ExerciseTopBar(
                        modifier = Modifier.align(Alignment.TopCenter),
                        instruction = stringResource(
                            id = ExerciseNameToInstructionResMapper.map(
                                exerciseName = ExerciseName.COMPLEX_SORT,
                                complexSortIsSimilar = state.items.firstOrNull()?.isSimilar ?: false
                            )
                        ),
                        timeProgress = state.timerState.timeUntilFinishedProgress,
                        onBack = { actioner(ComplexSortActions.Back) },
                        content = {
                            state.uiMessage?.let { message ->
                                when (message.message) {
                                    ComplexSortUiMessage.AnswerIsCorrect -> {
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

                                    ComplexSortUiMessage.AnswerIsNotCorrect -> {
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
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(16.dp)
            ) {
                ComplexSortItemUi(
                    item = state.items.firstOrNull() ?: ComplexSortItem.Test,
                    isClickable = false
                ) {}
            }

            LazyVerticalGrid(
                cells = GridCells.Adaptive(100.dp),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentPadding = PaddingValues(8.dp)
            ) {
                item {
                    ComplexSortItemUi(
                        modifier = Modifier.padding(end = 8.dp),
                        item = ComplexSortItem.ExampleFirst,
                        isClickable = true
                    ) {
                        actioner(ComplexSortActions.ItemClick(ComplexSortItem.ExampleFirst))
                    }
                }

                item {
                    ComplexSortItemUi(
                        modifier = Modifier.padding(end = 8.dp),
                        item = ComplexSortItem.ExampleSecond,
                        isClickable = true
                    ) {
                        actioner(ComplexSortActions.ItemClick(ComplexSortItem.ExampleSecond))
                    }
                }

                item {
                    ComplexSortItemUi(item = ComplexSortItem.ExampleThird, isClickable = true) {
                        actioner(ComplexSortActions.ItemClick(ComplexSortItem.ExampleThird))
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
        ComplexSort(state = ComplexSortViewState.Test, actioner = {}, onMessageShown = {})
    }
}