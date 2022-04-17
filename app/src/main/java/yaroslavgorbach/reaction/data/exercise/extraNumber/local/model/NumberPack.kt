package yaroslavgorbach.reaction.data.exercise.extraNumber.local.model

data class NumberPack(val numbers: List<Number>) {
    companion object {
        val Empty = NumberPack(emptyList())
        val Test = NumberPack(
            listOf(
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
                Number.Test,
            )
        )
    }
}