package vi.sukhov.scanner.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceStorage @Inject constructor(context: Context) : PreferenceStorage {

    companion object {
        const val PREFS_NAME = "ru.sukhov.scanner"
        const val PREFS_IS_DARK_MODE = "prefs_is_dark_mode"
    }

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        prefs.value.edit { putBoolean(PREFS_IS_DARK_MODE, isDarkMode) }
    }

    override suspend fun isDarkMode(): Flow<Boolean> {
        return flow {
            emit(prefs.value.getBoolean(PREFS_IS_DARK_MODE, false))
        }
    }

}
