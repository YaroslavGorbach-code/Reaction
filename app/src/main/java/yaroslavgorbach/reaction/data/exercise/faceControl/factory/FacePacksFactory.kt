package yaroslavgorbach.reaction.data.exercise.faceControl.factory

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SentimentDissatisfied
import androidx.compose.material.icons.outlined.SentimentSatisfied
import yaroslavgorbach.reaction.data.exercise.faceControl.model.Face
import yaroslavgorbach.reaction.data.exercise.faceControl.model.FacePack
import kotlin.random.Random

@ExperimentalStdlibApi
class FacePacksFactory {
    companion object {
        private const val FACES_IN_A_PACK = 30
    }

    fun create(): List<FacePack> {
        return buildList {
            repeat(1000) {
                this.add(
                    FacePack(buildList {
                        val extraIndex: Int = Random.nextInt(FACES_IN_A_PACK)

                        repeat(FACES_IN_A_PACK) { index ->
                            if (index == extraIndex) {
                                this.add(Face(Icons.Outlined.SentimentDissatisfied, true))
                            } else {
                                this.add(Face(Icons.Outlined.SentimentSatisfied, false))
                            }
                        }
                    })
                )
            }
        }
    }
}