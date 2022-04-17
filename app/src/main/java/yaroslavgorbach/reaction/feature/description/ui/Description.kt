package yaroslavgorbach.reaction.feature.description.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.common.ui.Toolbar
import yaroslavgorbach.reaction.feature.description.model.DescriptionActions
import yaroslavgorbach.reaction.feature.description.model.DescriptionViewState
import yaroslavgorbach.reaction.feature.description.presentation.DescriptionViewModel
import yaroslavgorbach.reaction.utill.singleClickable

@ExperimentalMaterialApi
@Composable
fun Description(
    exerciseName: ExerciseName,
    openExercise: (exerciseName: ExerciseName) -> Unit,
    onBackClick: () -> Unit,
) {
    val viewModel: DescriptionViewModel = hiltViewModel()

    Description(
        viewModel = viewModel,
        openExercise = openExercise,
        onBack = onBackClick
    )
}

@ExperimentalMaterialApi
@Composable
internal fun Description(
    viewModel: DescriptionViewModel,
    openExercise: (exerciseName: ExerciseName) -> Unit,
    onBack: () -> Unit,
) {
    val viewState = viewModel.state.collectAsState()

    Description(
        state = viewState.value,
    ) { action ->
        when (action) {
            is DescriptionActions.OpenExercise -> openExercise(action.exerciseName)
            is DescriptionActions.Back -> onBack()
            else -> viewModel.submitAction(action)
        }
    }
}

@ExperimentalMaterialApi
@Composable
internal fun Description(
    state: DescriptionViewState,
    actioner: (DescriptionActions) -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        Toolbar { actioner(DescriptionActions.Back) }

        Text(
            text = stringResource(id = state.exerciseName.res),
            style = MaterialTheme.typography.caption,
            fontSize = 40.sp,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Text(
            text = stringResource(id = state.descriptionRes),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .singleClickable {
                    actioner(DescriptionActions.OpenExercise(exerciseName = state.exerciseName))
                }) {

            Text(
                fontSize = 40.sp,
                color = MaterialTheme.colors.primary,
                text = stringResource(id = R.string.start).uppercase(),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.Center),
            )
        }

    }

}

@ExperimentalMaterialApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExercisesPreview() {
    ReactionTheme {
        Description(state = DescriptionViewState(R.string.description_rotation), actioner = {})
    }
}