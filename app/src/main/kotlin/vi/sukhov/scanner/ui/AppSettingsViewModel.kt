package vi.sukhov.scanner.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.gateway.PreferenceStorage
import vi.sukhov.scanner.data.gateway.UsersStorage
import vi.sukhov.scanner.entity.User
import javax.inject.Inject

@HiltViewModel
class AppSettingsViewModel @Inject constructor(
    private val preferences: PreferenceStorage,
    private val usersStorage: UsersStorage,
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    private var userID = ""

    // Получилось транзитивно
    init {
        viewModelScope.launch {
            try {
                preferences.getUser().collect {
                    userID = it.id ?: ""
                }
            } catch (e: Exception) {
                // Свалится т.к. по дефолту пусто
            }
        }
    }

    suspend fun isSigned() = preferences.isSigned()

    suspend fun saveSigned(isSigned: Boolean) {
        preferences.saveSign(isSigned)
    }

    // Выход из аккаунта
    fun signOut() {
        viewModelScope.launch {
            saveSigned(false)
            firebaseAuth.signOut()
        }
    }

    suspend fun isDarkMode() = preferences.isDarkMode()

    suspend fun saveDarkMode(isDark: Boolean) {
        preferences.saveDarkMode(isDark)
    }

    suspend fun observeUser(): Flow<User> = usersStorage.getUserById(userID)
}
