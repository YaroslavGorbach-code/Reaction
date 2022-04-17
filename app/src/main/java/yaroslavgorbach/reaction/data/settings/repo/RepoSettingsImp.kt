package yaroslavgorbach.reaction.data.settings.repo

import android.content.Context
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.reaction.data.settings.local.SettingsDataStore

class RepoSettingsImp(private val localDataStore: SettingsDataStore, private val context: Context) :
    RepoSettings {

    override fun observeIsFirstAppOpen(): Flow<Boolean> {
        return localDataStore.observeIsFirstAppOpen(context)
    }

    override suspend fun changeIsFirsAppOpen(isFirstOpen: Boolean) {
        localDataStore.changeIsFirstAppOpen(context, isFirstOpen)
    }
}