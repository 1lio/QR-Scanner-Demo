package vi.sukhov.scanner.data.local.prefs

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

     suspend fun isDarkMode(): Flow<Boolean>

     suspend fun saveDarkMode(isDarkMode: Boolean)
}