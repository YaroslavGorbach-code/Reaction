package yaroslavgorbach.reaction.data.exercise.rotation.model

import yaroslavgorbach.reaction.feature.exercise.common.model.YesNoChoseVariations

data class Tables(val firstTable: Table, val secondTable: Table, val areTheSame: Boolean) {
    companion object {
        val Test = Tables(Table.Test, Table.Test, false)
    }

    suspend fun checkAnswer(choseVariant: YesNoChoseVariations, isCorrect: suspend (isCorrect: Boolean) -> Unit) {
        when (choseVariant) {
            YesNoChoseVariations.YES -> isCorrect(areTheSame)
            YesNoChoseVariations.NO -> isCorrect(areTheSame.not())
        }
    }
}
