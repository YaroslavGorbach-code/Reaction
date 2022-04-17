package yaroslavgorbach.reaction.feature.exercise.rotation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yaroslavgorbach.reaction.feature.common.ui.theme.ReactionTheme
import yaroslavgorbach.reaction.data.exercise.rotation.model.Table

@ExperimentalFoundationApi
@Composable
fun Table(table: Table) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(table.width),
        modifier = Modifier
            .width(250.dp)
            .padding(16.dp)
            .rotate(table.rotationDegree.degree)
    ) {

        repeat(table.numberOfCells) { index ->
            item {
                if (table.filledCellsIndexes.any { it == index }) {
                    TableCell(color = Color.Red)
                } else {
                    TableCell(color = MaterialTheme.colors.onSurface)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun TablePreview() {
    ReactionTheme {
        Table(Table.Test)
    }
}