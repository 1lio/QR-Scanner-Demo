package vi.sukhov.scanner.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.repository.settings.SettingsRepository
import javax.inject.Inject

@HiltViewModel
class AppSettingsViewModel @Inject constructor(private val repository: SettingsRepository) :
    ViewModel() {

    // Костыль переделать! 0))

    private val _flowDarkMode: MutableStateFlow<Boolean> =
        MutableStateFlow(repository.isDarkModeEnabled())

    init {
        /*      GlobalScope.launch {
                  repository.flowIsDarkMode().collect {
                      _flowDarkMode.value = it
                  }
              }.start()*/
    }

    val isDarkMode: StateFlow<Boolean> = _flowDarkMode

    fun isDarkModeOn(): Boolean = repository.isDarkModeEnabled()

    fun flowIsDarkMode() = repository.flowIsDarkMode()

}
