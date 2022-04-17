package yaroslavgorbach.reaction.data.exercise.rotation.factory

import yaroslavgorbach.reaction.data.exercise.rotation.model.RotationDegree
import yaroslavgorbach.reaction.data.exercise.rotation.model.Table
import yaroslavgorbach.reaction.data.exercise.rotation.model.Tables
import kotlin.random.Random

@ExperimentalStdlibApi
class TablesFactory(private val width: Int = 5, private val height: Int = 5) {

    private val rotationDegreeVariations = RotationDegree.values().toList()

    fun create(): List<Tables> {
        return buildList {
            repeat(1000) {
                add(createTables(width = width, height = height))
            }
        }
    }

    private fun createTables(width: Int, height: Int): Tables {
        val areTablesTheSame = Random.nextBoolean()

        var paintedCellsIndexes = createPaintedCellsIndexes(width = width, height = height)

        val firstTable = Table(
            width = width,
            height = height,
            filledCellsIndexes = paintedCellsIndexes,
            rotationDegree = rotationDegreeVariations.random()
        )

        if (areTablesTheSame.not()) {
            paintedCellsIndexes = createPaintedCellsIndexes(width = width, height = height)
        }

        val secondTable = Table(
            width = width,
            height = height,
            filledCellsIndexes = paintedCellsIndexes,
            rotationDegree = rotationDegreeVariations.random()
        )

        return Tables(
            firstTable = firstTable,
            secondTable = secondTable,
            areTheSame = areTablesTheSame,
        )
    }

    private fun createPaintedCellsIndexes(width: Int, height: Int): HashSet<Int> {
        val numberOfCells = width * height
        val numberOfPaintedCells = Random.nextInt(3, numberOfCells - 3)
        val paintedCellsIndexes: HashSet<Int> = HashSet()

        while (paintedCellsIndexes.size != numberOfPaintedCells) {
            paintedCellsIndexes.add(Random.nextInt(0, numberOfCells))
        }

        return paintedCellsIndexes
    }
}