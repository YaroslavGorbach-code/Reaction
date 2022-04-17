package yaroslavgorbach.reaction.data.exercise.extraWord.model

data class WordPack(val words: List<Word>) {
    companion object {
        val Empty = WordPack(emptyList())
        val Test = WordPack(
            listOf(
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
                Word.Test,
            )
        )
    }
}