package vi.sukhov.scanner.data.local.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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

    private val dataStore = context.applicationContext.createDataStore(STORE_NAME)

    private companion object {
        const val STORE_NAME = "scanner_datastore"

        val KEY_IS_SIGNED = booleanPreferencesKey("prefs_is_signed")
        val KEY_IS_DARK_MODE = booleanPreferencesKey("prefs_is_dark_mode")
    }


    override suspend fun isSigned(): Flow<Boolean> = dataStore.savePref(KEY_IS_SIGNED, false)
    override suspend fun saveSign(isSigned: Boolean) {
        dataStore.editPref(KEY_IS_SIGNED, isSigned)
    }

    override suspend fun isDarkMode(): Flow<Boolean> = dataStore.savePref(KEY_IS_DARK_MODE, false)
    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        dataStore.editPref(KEY_IS_DARK_MODE, isDarkMode)
    }


    // Base Datastore function's
    private suspend fun <T> DataStore<Preferences>.editPref(key: Preferences.Key<T>, value: T) {
        this.edit {
            it[key] = value
        }
    }

    private fun <T> DataStore<Preferences>.savePref(key: Preferences.Key<T>, defValue: T): Flow<T> =
        this.data
            .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
            .map { it[key] ?: defValue }
}
