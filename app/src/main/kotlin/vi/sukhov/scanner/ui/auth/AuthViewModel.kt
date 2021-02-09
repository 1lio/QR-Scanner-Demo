package vi.sukhov.scanner.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import vi.sukhov.scanner.data.repository.auth.AuthRepository
import vi.sukhov.scanner.data.repository.settings.SettingsRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepository
) : ViewModel() {

    private val _flowStates: MutableStateFlow<UiStates> = MutableStateFlow(UiStates.EmptyState)
    val uiStates: StateFlow<UiStates> = _flowStates

    //LiveData
    fun signUpUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _flowStates.value = UiStates.Loading
                authRepo.getAuthCreds().createUserWithEmailAndPassword(email, password).await()
                _flowStates.value = UiStates.Success
            } catch (ex: Exception) {
                _flowStates.value = UiStates.Error(ex.toString())
            }

        }

    }

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _flowStates.value = UiStates.Loading
                authRepo.getAuthCreds().signInWithEmailAndPassword(email, password).await()
                _flowStates.value = UiStates.Success
            } catch (ex: Exception) {
                _flowStates.value = UiStates.Error(ex.toString())
            }
        }
    }

    sealed class UiStates {
        object Loading : UiStates()
        object Success : UiStates()
        data class Error(var exception: String) : UiStates()
        object EmptyState : UiStates()
    }
}