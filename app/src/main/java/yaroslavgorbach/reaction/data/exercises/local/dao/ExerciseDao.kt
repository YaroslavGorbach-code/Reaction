package yaroslavgorbach.reaction.data.exercises.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.reaction.data.exercises.local.model.Exercise
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM Exercise")
    fun observe(): Flow<List<Exercise>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(exercises: List<Exercise>)

    @Update
    suspend fun update(exercise: Exercise)

    @Query("SELECT * FROM Exercise WHERE name = :exerciseName")
    suspend fun get(exerciseName: ExerciseName): Exercise

}