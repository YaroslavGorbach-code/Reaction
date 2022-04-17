package yaroslavgorbach.reaction.feature.exercise.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.feature.common.ui.Toolbar
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme

@Composable
fun ExerciseTopBar(
    modifier: Modifier = Modifier,
    instruction: String,
    timeProgress: Float,
    onBack: () -> Unit,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Column(modifier = modifier) {
        Toolbar { onBack() }

        Text(
            text = instruction,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )

        LinearProgressIndicator(
            progress = timeProgress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )

        content()
    }
}

@Composable
@Preview(showSystemUi = false)
fun ExerciseTopBarPreview() {
    Surface() {
        ReactionTheme {
            ExerciseTopBar(
                instruction = stringResource(id = R.string.description_rotation),
                timeProgress = 0.5f,
                onBack = {}
            )
        }
    }

}