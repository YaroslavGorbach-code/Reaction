package yaroslavgorbach.reaction.feature.exercise.extraNumber.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yaroslavgorbach.reaction.data.exercise.extraNumber.local.model.Number
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme

@Composable
fun NumberItem(number: Number, onNumberClick: () -> Unit) {
        Box(modifier = Modifier
            .padding(4.dp)
            .clickable { onNumberClick() }
            .background(
                color = MaterialTheme.colors.onSurface,
                shape = MaterialTheme.shapes.medium
            )
            .size(60.dp)) {

            Text(
                text = number.number.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.caption
            )
        }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NumberItemPrev(number: Number = Number.Test, onNumberClick: () -> Unit = {}) {

    ReactionTheme {
        NumberItem(number = number, onNumberClick = onNumberClick)
    }
}