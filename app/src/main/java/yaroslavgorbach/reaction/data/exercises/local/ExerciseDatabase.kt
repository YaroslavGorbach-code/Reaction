package yaroslavgorbach.reaction.data.exercises.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import yaroslavgorbach.reaction.data.exercises.local.dao.ExerciseDao
import yaroslavgorbach.reaction.data.exercises.local.model.Exercise

@TypeConverters(ExerciseNameConverter::class)
@Database(
    entities = [Exercise::class],
    version = 1
)
abstract class ExerciseDatabase : RoomDatabase() {
    companion object {

        @Volatile
        private var INSTANCE: ExerciseDatabase? = null

        fun getInstance(context: Application): ExerciseDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ExerciseDatabase::class.java,
                        "db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract val exercise: ExerciseDao
}