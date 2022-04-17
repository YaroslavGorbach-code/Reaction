package yaroslavgorbach.reaction.feature.exercise.extraWord.model

import yaroslavgorbach.reaction.data.exercise.extraWord.model.Word

sealed class ExtraWordActions {
    class WordClick(val word: Word) : ExtraWordActions()
    object Back : ExtraWordActions()
    object Repeat : ExtraWordActions()
    object FinishExercise : ExtraWordActions()
}