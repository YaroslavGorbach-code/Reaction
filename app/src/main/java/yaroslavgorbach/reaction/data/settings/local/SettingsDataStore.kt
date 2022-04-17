package yaroslavgorbach.reaction.data.settings.local

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    suspend fun changeIsFirstAppOpen(context: Context, isFirstAppOpen: Boolean)

    fun observeIsFirstAppOpen(context: Context): Flow<Boolean>
}