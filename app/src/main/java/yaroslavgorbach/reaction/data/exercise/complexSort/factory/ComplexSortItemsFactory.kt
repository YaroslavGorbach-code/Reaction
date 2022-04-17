package yaroslavgorbach.reaction.data.exercise.complexSort.factory

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessibilityNew
import androidx.compose.material.icons.outlined.Hiking
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.ui.graphics.Color
import yaroslavgorbach.reaction.data.exercise.complexSort.model.ComplexSortItem
import kotlin.random.Random

@ExperimentalStdlibApi
class ComplexSortItemsFactory {
    private val icons = listOf(
        Icons.Outlined.AccessibilityNew,
        Icons.Outlined.SelfImprovement,
        Icons.Outlined.Hiking,
    )

    private val colors = listOf(
        Color.Blue,
        Color.Red,
        Color.Green,
    )

    private val similarItems = listOf(
        ComplexSortItem.ExampleFirst,
        ComplexSortItem.ExampleSecond,
        ComplexSortItem.ExampleThird
    )

    private val noSimilarItems = createRandomNoSimilarItems()


    @ExperimentalStdlibApi
    fun create(): List<ComplexSortItem> {

        return buildList {
            repeat(1000) {
                val isSimilar = Random.nextBoolean()

                if (isSimilar) {
                    this.add(similarItems.random().copy(isSimilar = true))
                } else {
                    this.add(noSimilarItems.random().copy(isSimilar = false))
                }
            }
        }
    }

    private fun createRandomNoSimilarItems(): List<ComplexSortItem> {
        return buildList {
            repeat(1000) {
                add(
                    ComplexSortItem(
                        icon =  icons.random(),
                        color = colors.random(),
                        isSimilar = false
                    )
                )
            }
        }.filter { it != ComplexSortItem.ExampleFirst && it != ComplexSortItem.ExampleSecond && it != ComplexSortItem.ExampleThird }
    }
}