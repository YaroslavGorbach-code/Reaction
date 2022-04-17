package yaroslavgorbach.reaction.di.data.exerciseslist

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.reaction.data.exercises.local.ExerciseDatabase
import yaroslavgorbach.reaction.data.exercises.local.dao.ExerciseDao
import yaroslavgorbach.reaction.data.exercises.repo.RepoExercises
import yaroslavgorbach.reaction.data.exercises.repo.RepoExercisesImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataExercisesModule {

    @Provides
    fun provideExercisesRepo(dao: ExerciseDao): RepoExercises {
        return RepoExercisesImp(dao)
    }

    @Provides
    fun provideExerciseDatabase(app: Application): ExerciseDatabase {
        return ExerciseDatabase.getInstance(app)
    }

    @Provides
    fun provideExerciseDao(db: ExerciseDatabase): ExerciseDao {
        return db.exercise
    }
}