package vi.sukhov.scanner.data.local.prefs

import android.content.Context
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesDataStore @Inject constructor(context: Context) : PreferenceStorage {

    companion object {
        private const val STORE_NAME = "scanner_storage"
        val IS_FIRS_RUN = booleanPreferencesKey("is_first_run")
    }

    private val dataStore = context.createDataStore(
        name = STORE_NAME,
        migrations = listOf(SharedPreferencesMigration(context, "PREFS_NAME"))
    )

    override var isFirstLogin: Flow<Boolean> = dataStore.data
        .catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else throw  exception

    }.map { it[IS_FIRS_RUN] ?: false }

}
