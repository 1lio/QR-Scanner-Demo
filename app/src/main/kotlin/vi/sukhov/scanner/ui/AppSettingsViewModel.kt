package vi.sukhov.scanner.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.repository.settings.SettingsRepository
import javax.inject.Inject

@HiltViewModel
class AppSettingsViewModel @Inject constructor(private val repository: SettingsRepository) :
    ViewModel() {

    private val _flowDarkMode: MutableStateFlow<Boolean> =
        MutableStateFlow(repository.isDarkModeEnabled())

    init {
        viewModelScope.launch {
            repository.flowIsDarkMode().collect {
                _flowDarkMode.value = it
            }
        }
    }

    val isDarkMode: StateFlow<Boolean> = _flowDarkMode

    fun isDarkModeOn(): Boolean = repository.isDarkModeEnabled()

    fun flowIsDarkMode() = repository.flowIsDarkMode()

}
