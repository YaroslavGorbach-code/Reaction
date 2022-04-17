package yaroslavgorbach.reaction.feature.exercise.numbersAndLetters.model

import yaroslavgorbach.reaction.feature.exercise.common.model.YesNoChoseVariations

sealed class NumbersAndLettersActions {
    class Chose(val chose: YesNoChoseVariations) : NumbersAndLettersActions()
    object Back : NumbersAndLettersActions()
    object Repeat : NumbersAndLettersActions()
    object FinishExercise : NumbersAndLettersActions()
}