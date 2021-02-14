package vi.sukhov.scanner.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.gateway.UsersStorage
import vi.sukhov.scanner.data.repository.SettingsRepository
import vi.sukhov.scanner.entity.User
import javax.inject.Inject

@HiltViewModel
class AppSettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val firebaseAuth: FirebaseAuth,
    private val usersStorage: UsersStorage
) : ViewModel() {

    private var userID = ""

    // Получилось транзитивно

    init {
        viewModelScope.launch {
            repository.getUser().collect {
                userID = it.id ?: ""
            }
        }
    }

    suspend fun isSigned() = repository.isSigned()

    suspend fun saveSigned(isSigned: Boolean) {
        repository.saveSign(isSigned)
    }

    // Выход из аккаунта
    fun signOut() {
        viewModelScope.launch {
            saveSigned(false)
            firebaseAuth.signOut()
        }
    }

    suspend fun isDarkMode() = repository.isDarkMode()

    suspend fun saveDarkMode(isDark: Boolean) {
        repository.saveDarkMode(isDark)
    }

    suspend fun observeUser(): Flow<User> = usersStorage.getUserById(userID)
}
