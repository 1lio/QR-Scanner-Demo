package vi.sukhov.scanner.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.repository.settings.SettingsRepository
import javax.inject.Inject

@HiltViewModel
class AppSettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

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
}
