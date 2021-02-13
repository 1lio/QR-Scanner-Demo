package vi.sukhov.scanner.data.local.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreStorage @Inject constructor(context: Context) : PreferenceStorage {

    companion object {
        const val STORE_NAME = "scanner_datastore"
        val KEY_IS_DARK_MODE = booleanPreferencesKey("prefs_is_dark_mode")
    }

    private val dataStore = context.applicationContext.createDataStore(STORE_NAME)

    override suspend fun isDarkMode(): Flow<Boolean> =
        dataStore.data
            .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
            .map { it[KEY_IS_DARK_MODE] ?: false }


    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        dataStore.edit {
            it[KEY_IS_DARK_MODE] = isDarkMode
        }
    }

}
