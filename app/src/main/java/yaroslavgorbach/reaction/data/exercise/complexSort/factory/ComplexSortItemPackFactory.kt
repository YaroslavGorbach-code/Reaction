package yaroslavgorbach.reaction.data.exercise.complexSort.factory

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import yaroslavgorbach.reaction.data.exercise.complexSort.model.ComplexSortItem
import yaroslavgorbach.reaction.data.exercise.complexSort.model.ComplexSortItemPack
import kotlin.random.Random

class ComplexSortItemPackFactory {
    private val icons = listOf(
        Icons.Default.AccessAlarm,
        Icons.Default.AccessibilityNew,
        Icons.Default.AccountBox,
    )

    private val colors = listOf(
        Color.Blue,
        Color.Red,
        Color.Green,
    )

    @ExperimentalStdlibApi
    fun create(): List<ComplexSortItemPack> {
        return buildList {
            repeat(1000) {
                this.add(
                    ComplexSortItemPack(buildList {
                        add(
                            ComplexSortItem(
                                icon = icons.random(), color = colors.random(), isSimilar = Random.nextBoolean()
                            )
                        )
                    })
                )
            }
        }
    }
}