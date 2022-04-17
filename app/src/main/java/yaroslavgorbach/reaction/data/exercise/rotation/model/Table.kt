package yaroslavgorbach.reaction.data.exercise.rotation.model

data class Table(
    val width: Int,
    val height: Int,
    val filledCellsIndexes: Set<Int>,
    val numberOfCells: Int = width * height,
    val rotationDegree: RotationDegree
) {
    companion object {
        val Test = Table(5, 5, setOf(1,3,5,8), rotationDegree = RotationDegree.NO)
    }
}