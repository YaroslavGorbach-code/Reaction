package yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model

import yaroslavgorbach.reaction.data.exercise.complexSort.model.ComplexSortItem

sealed class ComplexSortActions {
    class ItemClick(val item: ComplexSortItem) : ComplexSortActions()
    object Back : ComplexSortActions()
    object Repeat : ComplexSortActions()
    object FinishExercise : ComplexSortActions()
}