package yaroslavgorbach.reaction.feature.exercise.faceControl.model

import yaroslavgorbach.reaction.feature.exercise.common.model.WinRule

object FaceControlWinRule : WinRule {
    override val minRounds: Int
        get() = 35

    override val minCorrectPresent: Int
        get() = 93
}