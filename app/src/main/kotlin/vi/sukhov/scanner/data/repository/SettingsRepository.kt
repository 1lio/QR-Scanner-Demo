package vi.sukhov.scanner.data.repository

import kotlinx.coroutines.flow.Flow
import vi.sukhov.scanner.data.gateway.PreferenceStorage
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val storage: PreferenceStorage) :
    PreferenceStorage {

    override suspend fun isSigned(): Flow<Boolean> = storage.isSigned()
    override suspend fun saveSign(isSigned: Boolean) { storage.saveSign(isSigned) }

    override suspend fun isDarkMode(): Flow<Boolean> = storage.isDarkMode()
    override suspend fun saveDarkMode(isDarkMode: Boolean) { storage.saveDarkMode(isDarkMode) }

}