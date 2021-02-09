package vi.sukhov.scanner.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import vi.sukhov.scanner.data.repository.settings.SettingsRepository
import javax.inject.Inject

@HiltViewModel
class AppSettingsViewModel @Inject constructor(private val repository: SettingsRepository) :
    ViewModel() {

    private val _flowDarkMode: MutableStateFlow<Boolean> =
        MutableStateFlow(repository.isDarkModeEnabled())

    val isDarkMode: StateFlow<Boolean> = _flowDarkMode

    fun isDarkModeOn(): Boolean = repository.isDarkModeEnabled()

}
