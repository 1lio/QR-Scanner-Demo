package vi.sukhov.scanner.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import vi.sukhov.scanner.core.common.UiStates
import vi.sukhov.scanner.data.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepo: AuthRepository) : ViewModel() {

    private val _flowStates: MutableStateFlow<UiStates> = MutableStateFlow(UiStates.EmptyState)
    val uiStates: StateFlow<UiStates> = _flowStates

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                _flowStates.value = UiStates.Loading
                authRepo.signUpWithEmailAndPassword(email, password)
                _flowStates.value = UiStates.Success
            } catch (ex: Exception) {
                _flowStates.value = UiStates.Error(ex.toString())
            }
        }

    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                _flowStates.value = UiStates.Loading
                authRepo.signInWithEmailAndPassword(email, password)
                _flowStates.value = UiStates.Success
            } catch (ex: Exception) {
                _flowStates.value = UiStates.Error(ex.toString())
            }
        }
    }
}
