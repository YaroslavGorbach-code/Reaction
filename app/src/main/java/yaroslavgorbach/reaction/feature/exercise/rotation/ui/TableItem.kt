package yaroslavgorbach.reaction.feature.exercise.rotation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme

@Composable
fun TableCell(color: Color) {
    Box(
        modifier = Modifier
            .size(35.dp)
            .padding(2.dp)
            .background(color = color, shape = MaterialTheme.shapes.small)
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun TableCellPreview() {
    ReactionTheme {
        TableCell(Color.Red)
    }
}