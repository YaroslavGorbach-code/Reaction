package yaroslavgorbach.reaction.di.data.settings

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.reaction.data.exercises.local.ExerciseDatabase
import yaroslavgorbach.reaction.data.exercises.local.dao.ExerciseDao
import yaroslavgorbach.reaction.data.exercises.repo.RepoExercises
import yaroslavgorbach.reaction.data.exercises.repo.RepoExercisesImp
import yaroslavgorbach.reaction.data.settings.local.SettingsDataStore
import yaroslavgorbach.reaction.data.settings.local.SettingsDataStoreImp
import yaroslavgorbach.reaction.data.settings.repo.RepoSettings
import yaroslavgorbach.reaction.data.settings.repo.RepoSettingsImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSettingsModule {

    @Provides
    fun provideSettingsRepo(app: Application, settingsDataStore: SettingsDataStore): RepoSettings {
        return RepoSettingsImp(settingsDataStore, app)
    }

    @Provides
    fun provideSettingsDatastore(): SettingsDataStore {
        return SettingsDataStoreImp()
    }
}