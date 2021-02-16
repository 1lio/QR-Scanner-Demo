package vi.sukhov.scanner.data.local.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import vi.sukhov.scanner.data.gateway.PreferenceStorage
import vi.sukhov.scanner.entity.User
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

        object UserScheme {
            val FIELD_ID = stringPreferencesKey("user_id")
            val FIELD_NAME = stringPreferencesKey("user_name")
        }
    }

    override suspend fun isSigned(): Flow<Boolean> = dataStore.savePref(KEY_IS_SIGNED, false)
    override suspend fun saveSign(isSigned: Boolean) {
        dataStore.editPref(KEY_IS_SIGNED, isSigned)
    }

    override suspend fun getUser(): Flow<User> {
        return dataStore.data.map {
            val id: String? = it[UserScheme.FIELD_ID]
            val name: String? = it[UserScheme.FIELD_NAME]
            User(id!!, name)
        }
    }

    override suspend fun saveUser(user: User) {
        dataStore.edit { prefs ->
            prefs[UserScheme.FIELD_ID] = user.id ?: ""
            prefs[UserScheme.FIELD_NAME] = user.name ?: ""
        }
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
