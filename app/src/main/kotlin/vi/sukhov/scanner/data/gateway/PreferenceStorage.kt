package vi.sukhov.scanner.data.gateway

import kotlinx.coroutines.flow.Flow
import vi.sukhov.scanner.entity.User

interface PreferenceStorage {

    // Auth
    suspend fun isSigned(): Flow<Boolean>
    suspend fun saveSign(isSigned:Boolean)

    // User
    suspend fun getUser(): Flow<User>
    suspend fun saveUser(user: User)

    // Settings
    suspend fun isDarkMode(): Flow<Boolean>
    suspend fun saveDarkMode(isDarkMode: Boolean)
}