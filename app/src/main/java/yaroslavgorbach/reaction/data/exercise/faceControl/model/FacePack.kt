package yaroslavgorbach.reaction.data.exercise.faceControl.model

data class FacePack(val faces: List<Face>) {
    companion object {
        val Empty = FacePack(emptyList())
        val Test = FacePack(
            listOf(
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
                Face.Test,
            )
        )
    }
}