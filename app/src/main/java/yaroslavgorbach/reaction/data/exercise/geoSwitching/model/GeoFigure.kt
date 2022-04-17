package yaroslavgorbach.reaction.data.exercise.geoSwitching.model

import androidx.compose.ui.graphics.Color
import yaroslavgorbach.reaction.feature.exercise.common.model.YesNoChoseVariations

data class GeoFigure(val figure: FigureVariant, val color: Color, val taskVariant: TaskVariant) {

    companion object {
        val Test = GeoFigure(FigureVariant.SQUARE, Color.Red, TaskVariant.IS_BLUE)
    }

    inline fun checkAnswer(answer: YesNoChoseVariations, onResult: (isCorrect: Boolean) -> Unit) {
        when (answer) {
            YesNoChoseVariations.NO -> {
                when (taskVariant) {
                    TaskVariant.IS_SQUARE -> onResult(figure != FigureVariant.SQUARE)
                    TaskVariant.IS_BLUE -> onResult(color != Color.Blue)
                }
            }
            YesNoChoseVariations.YES -> {
                when (taskVariant) {
                    TaskVariant.IS_SQUARE -> onResult(figure == FigureVariant.SQUARE)
                    TaskVariant.IS_BLUE -> onResult(color == Color.Blue)
                }
            }
        }
    }
}