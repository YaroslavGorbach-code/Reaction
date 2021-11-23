package yaroslavgorbach.reaction.data.training.model

import android.util.Log
import yaroslavgorbach.reaction.data.listexercises.local.model.Exercise
import yaroslavgorbach.reaction.data.listexercises.local.model.ExerciseName

data class TrainingExercise(private val exercise: Exercise, val aim: Int, val completed: Int) {

    companion object {
        val Test = TrainingExercise(
            exercise = Exercise.Test, aim = 10, completed = 6
        )
    }

    val name: ExerciseName
        get() = exercise.name

    val progressFloat: Float
        get() = (aim.toFloat() / completed.toFloat())

    val progressInt: Int
        get() = ((completed.toFloat() / aim.toFloat()) * 100f).toInt()

    val isFinished: Boolean
        get() = completed >= aim
}
