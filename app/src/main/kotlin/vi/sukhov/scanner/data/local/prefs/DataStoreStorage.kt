package vi.sukhov.scanner.data.local.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreStorage @Inject constructor(context: Context) : PreferenceStorage {

    companion object {
        const val STORE_NAME = "scanner_datastore"
        val KEY_TIME_LOADED_AT = longPreferencesKey("prefs_data_loaded_at")
        val KEY_IS_DARK_MODE = booleanPreferencesKey("prefs_is_dark_mode")
    }

    private val dataStore = context.applicationContext.createDataStore(STORE_NAME)

    override var timeLoadedAt: Long
        set(value) {
            GlobalScope.launch {
                dataStore.edit {
                    it[KEY_TIME_LOADED_AT] = value
                }
            }
        }
        get() {
            var result: Long = 0
            runBlocking {
                dataStore.data.collect {
                    result = it[KEY_TIME_LOADED_AT] ?: 0
                }
            }

            return result
        }

    override var isDarkMode: Boolean
        set(value) {
            GlobalScope.launch(Dispatchers.IO + Job()) {
                dataStore.edit {
                    it[KEY_IS_DARK_MODE] = value
                }
            }
        }
        get() {
            var result = false
            GlobalScope.launch(Dispatchers.IO + Job()) {
                dataStore.data.collect {
                    result = it[KEY_IS_DARK_MODE] ?: false
                }
            }

            return result
        }

    suspend fun flowDarkMode(): Flow<Unit> = dataStore.data.catch { exception ->
        if (exception is IOException) emit(emptyPreferences()) else throw exception
    }.map { it[KEY_IS_DARK_MODE] }
}
