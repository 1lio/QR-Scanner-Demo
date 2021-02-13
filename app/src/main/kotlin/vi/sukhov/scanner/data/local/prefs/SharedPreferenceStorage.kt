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
        const val PREFS_IS_SIGNED = "prefs_is_signed"
        const val PREFS_IS_DARK_MODE = "prefs_is_dark_mode"
    }

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override suspend fun isDarkMode(): Flow<Boolean> = flow {
        emit(prefs.value.getBoolean(PREFS_IS_DARK_MODE, false))
    }

    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        prefs.value.edit { putBoolean(PREFS_IS_DARK_MODE, isDarkMode) }
    }


    override suspend fun isSigned(): Flow<Boolean> = flow {
        emit(prefs.value.getBoolean(PREFS_IS_SIGNED, false))
    }

    override suspend fun saveSign(isSigned: Boolean) {
        prefs.value.edit { putBoolean(PREFS_IS_SIGNED, isSigned) }
    }

}
