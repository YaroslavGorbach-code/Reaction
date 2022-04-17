package yaroslavgorbach.reaction.feature.exercise.common.model

interface WinRule {
    val minRounds: Int
    val minCorrectPresent: Int

    companion object Test : WinRule {
        override val minRounds: Int
            get() = 1

        override val minCorrectPresent: Int
            get() = 0
    }
}