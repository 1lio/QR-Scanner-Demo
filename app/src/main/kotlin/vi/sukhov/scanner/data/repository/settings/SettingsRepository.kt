package vi.sukhov.scanner.data.repository.settings

import kotlinx.coroutines.flow.Flow
import vi.sukhov.scanner.data.local.prefs.PreferenceStorage
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val preferenceStorage: PreferenceStorage) :
    PreferenceStorage {

    override suspend fun isDarkMode(): Flow<Boolean> {
        return preferenceStorage.isDarkMode()
    }

    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        preferenceStorage.saveDarkMode(isDarkMode)
    }

}