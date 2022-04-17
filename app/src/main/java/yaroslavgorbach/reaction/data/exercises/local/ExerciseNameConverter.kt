package yaroslavgorbach.reaction.data.exercises.local

import androidx.room.TypeConverter
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName

object ExerciseNameConverter {
    @TypeConverter
    fun toName(value: String) = enumValueOf<ExerciseName>(value)

    @TypeConverter
    fun fromName(value: ExerciseName) = value.name
}