package yaroslavgorbach.reaction.feature.esercises.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yaroslavgorbach.reaction.data.exercises.local.model.Exercise
import yaroslavgorbach.reaction.feature.common.ui.CircularProgressIndicatorWithContent
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme
import yaroslavgorbach.reaction.utill.singleClickable

@ExperimentalMaterialApi
@Composable
fun ExerciseItem(exercise: Exercise, onExerciseClick: (isAvailable: Boolean) -> Unit) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(110.dp)
        .background(color = MaterialTheme.colors.onSurface, shape = MaterialTheme.shapes.medium)
        .singleClickable { onExerciseClick(exercise.isAvailable) }
        .padding(8.dp)
    )
    {
        Text(
            text = stringResource(id = exercise.name.res),
            style = MaterialTheme.typography.caption,
            fontSize = 20.sp,
            modifier = Modifier.weight(0.5f)
        )

        Box(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = exercise.iconRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(30.dp)
                    .align(Alignment.CenterStart)
            )

            CircularProgressIndicatorWithContent(
                progress = exercise.nextAvailabilityProgress,
                isVisible = exercise.isAvailable && exercise.isNextExerciseAvailable.not(),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterEnd)
            ) {
                if (exercise.isAvailable) {
                    IconWithBeget(exercise.numberOfWins.toString())
                } else {
                    Icon(
                        Icons.Default.Lock, contentDescription = null,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}

@Composable
private fun BoxScope.IconWithBeget(budgetText: String) {
    Box(
        modifier = Modifier.Companion
            .align(Alignment.Center)
            .size(32.dp)
    ) {
        Icon(
            Icons.Default.Star,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.Center),
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = CircleShape
                )
                .size(16.dp)
        ) {
            Text(
                text = budgetText,
                fontSize = 7.sp,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ExerciseItemPreview(exercise: Exercise = Exercise.Test) {
        ReactionTheme {
            Surface {
                ExerciseItem(exercise = exercise) {}
            }
        }
}