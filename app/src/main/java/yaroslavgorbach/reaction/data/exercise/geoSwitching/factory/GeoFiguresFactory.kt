package yaroslavgorbach.reaction.data.exercise.geoSwitching.factory

import androidx.compose.ui.graphics.Color
import yaroslavgorbach.reaction.data.exercise.geoSwitching.model.FigureVariant
import yaroslavgorbach.reaction.data.exercise.geoSwitching.model.GeoFigure
import yaroslavgorbach.reaction.data.exercise.geoSwitching.model.TaskVariant

@ExperimentalStdlibApi
class GeoFiguresFactory() {

    private val colors = listOf(Color.Green, Color.Red, Color.Blue)
    private val figures = FigureVariant.values().toList()
    private val tasks = TaskVariant.values().toList()

    fun create(): List<GeoFigure> {
        return buildList {
            repeat(1000) {
                add(GeoFigure(figure = figures.random(), color = colors.random(), tasks.random()))
            }
        }
    }
}