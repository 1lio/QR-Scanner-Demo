package vi.sukhov.scanner.data.repository.settings

import vi.sukhov.scanner.data.local.prefs.PreferenceStorage
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val preferenceStorage: PreferenceStorage) {

    fun isDarkModeEnabled() : Boolean {
        return preferenceStorage.isDarkMode
    }

    fun setThemeMode(isDarkMode: Boolean) {
        preferenceStorage.isDarkMode = isDarkMode
    }
}