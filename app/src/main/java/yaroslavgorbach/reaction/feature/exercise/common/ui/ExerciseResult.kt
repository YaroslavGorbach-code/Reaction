package yaroslavgorbach.reaction.feature.exercise.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.common.ui.Toolbar
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme
import yaroslavgorbach.reaction.feature.exercise.common.model.FinishExerciseState


@Composable
fun ExerciseResult(
    finishExerciseState: FinishExerciseState,
    onBackClick: () -> Unit,
    onRepeatExercise: () -> Unit
) {

    Column(Modifier.fillMaxSize()) {
        Toolbar { onBackClick() }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(color = MaterialTheme.colors.onSurface, shape = RoundedCornerShape(10))
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.result),
                style = MaterialTheme.typography.caption,
                fontSize = 45.sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(finishExerciseState.name.res),
                style = MaterialTheme.typography.body1,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Row(
                Modifier
                    .align(CenterHorizontally)
                    .wrapContentSize()
                    .padding(24.dp)
            ) {

                Box {
                    Text(
                        text = finishExerciseState.progressString,
                        modifier = Modifier.align(Center),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )

                    CircularProgressIndicator(
                        progress = 1f,
                        color = Color.Red.copy(alpha = 0.4f),
                        modifier = Modifier.size(60.dp),
                        strokeWidth = 4.dp
                    )

                    CircularProgressIndicator(
                        progress = finishExerciseState.correctPresent,
                        color = Color.Green,
                        modifier = Modifier.size(60.dp),
                        strokeWidth = 4.dp
                    )
                }

                Column(
                    Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically)
                ) {

                    Text(
                        text = stringResource(id = R.string.number_of_rounds) + " ${finishExerciseState.summaryPoints}",
                        style = MaterialTheme.typography.caption,
                        fontSize = 18.sp
                    )

                    Text(
                        text = stringResource(id = R.string.correct) + " ${finishExerciseState.pointsCorrect}",
                        style = MaterialTheme.typography.body1,
                        fontSize = 12.sp
                    )

                    Text(
                        text = stringResource(id = R.string.incorrect) + " ${finishExerciseState.pointsIncorrect}",
                        style = MaterialTheme.typography.body1,
                        fontSize = 12.sp
                    )
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(Center)
                    .padding(bottom = 100.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(100.dp)
                        .align(CenterHorizontally),
                    imageVector = finishExerciseState.icon,
                    tint = MaterialTheme.colors.primary,
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = if (finishExerciseState.isWin)
                        stringResource(id = R.string.result_good)
                    else stringResource(
                        id = R.string.result_bad,
                        finishExerciseState.winRule.minRounds,
                        finishExerciseState.winRule.minCorrectPresent
                    ),
                    style = MaterialTheme.typography.body1,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }

            Row(
                modifier = Modifier
                    .align(BottomCenter)
                    .padding(8.dp),
            ) {
                Button(
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(30),
                    onClick = { onBackClick() },
                    colors = ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(id = R.string.to_menu),
                        fontSize = 16.sp
                    )
                }

                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = { onRepeatExercise() },
                    shape = RoundedCornerShape(30),
                    colors = ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(id = R.string.one_more_time),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ExerciseResultPreview() {
    ReactionTheme {
        ExerciseResult(finishExerciseState = FinishExerciseState(name = ExerciseName.NO_NAME),
            onBackClick = {}, onRepeatExercise = {})
    }
}