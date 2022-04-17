package yaroslavgorbach.reaction.feature.exercise.common.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yaroslavgorbach.reaction.R
import yaroslavgorbach.reaction.feature.exercise.common.model.YesNoChoseVariations

@Composable
fun YesNoBottomButtons(modifier: Modifier = Modifier, onClick: (variant: YesNoChoseVariations) -> Unit) {
    Row(
        modifier = modifier
    ) {
        Button(
            shape = RoundedCornerShape(30),
            onClick = { onClick(YesNoChoseVariations.NO) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        ) {
            Text(text = stringResource(id = R.string.no), fontSize = 24.sp)
        }

        Button(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
                .weight(0.5f),
            shape = RoundedCornerShape(30),
            onClick = { onClick(YesNoChoseVariations.YES) }
        ) {
            Text(text = stringResource(id = R.string.yes), fontSize = 24.sp)
        }
    }
}