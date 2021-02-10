package vi.sukhov.scanner.data.local.prefs

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    var timeLoadedAt: Long
    var isDarkMode: Boolean

     fun flowIsDarkMode(): Flow<Boolean>
}