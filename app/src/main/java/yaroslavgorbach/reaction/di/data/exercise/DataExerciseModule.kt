package yaroslavgorbach.reaction.di.data.exercise

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.reaction.data.exercise.repo.RepoExercise
import yaroslavgorbach.reaction.data.exercise.repo.RepoExerciseImp

@Module
@InstallIn(SingletonComponent::class)
class DataExerciseModule {

    @ExperimentalStdlibApi
    @Provides
    fun provideExerciseRepo(application: Application): RepoExercise {
        return RepoExerciseImp(application)
    }

}