package vi.sukhov.scanner.ui

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import vi.sukhov.scanner.data.repository.settings.SettingsRepository
import javax.inject.Inject

@HiltViewModel
class AppSettingsViewModel @Inject constructor(private val repository: SettingsRepository) : ViewModel() {

    @Inject lateinit var firebaseAuth: FirebaseAuth

    suspend fun getDarkMode() = repository.isDarkMode()

    suspend fun saveDarkMode(isDark: Boolean) {
        repository.saveDarkMode(isDark)
    }

    // Выход из аккаунта
    fun signOut() {
        firebaseAuth.signOut()
    }
}
