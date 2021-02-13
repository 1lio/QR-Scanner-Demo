package vi.sukhov.scanner.data.local.prefs

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    // Auth
    suspend fun isSigned(): Flow<Boolean>
    suspend fun saveSign(isSigned:Boolean)

    // Settings
    suspend fun isDarkMode(): Flow<Boolean>
    suspend fun saveDarkMode(isDarkMode: Boolean)
}