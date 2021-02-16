package vi.sukhov.scanner.ui.home.settings

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentSettingsBinding
import vi.sukhov.scanner.ui.AppSettingsViewModel
import vi.sukhov.scanner.ui.auth.AuthActivity

// Основыные настройки приложения

@AndroidEntryPoint
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val viewModel: AppSettingsViewModel by viewModels()
    private val binding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingSetting()    // Загружаем графическое отражение настроек

        // Устанавливаем обработчики
        binding.settingsDarkModeSwitch.setOnCheckedChangeListener(::switchDarkMode)
        binding.staySignedSwitch.setOnCheckedChangeListener(::switchStaySigned)
        binding.signOut.setOnClickListener(::signOut)
    }

    private fun switchDarkMode(buttonView: CompoundButton, isChecked: Boolean) {
        lifecycleScope.launch {
            viewModel.saveDarkMode(isChecked)
        }
    }


    private fun switchStaySigned(buttonView: CompoundButton, isChecked: Boolean) {
        lifecycleScope.launch {
            viewModel.saveSigned(isChecked)
        }
    }

    private fun loadingSetting() {
        lifecycleScope.launch {

            viewModel.isDarkMode().collect {
                binding.settingsDarkModeSwitch.isChecked = it
            }

        }

        lifecycleScope.launch {
            viewModel.isSigned().collect {
                binding.staySignedSwitch.isChecked = it
            }
        }

        lifecycleScope.launch {
            viewModel.observeUser().collect {
                binding.user.text = it.name ?: ""
            }
        }
    }

    private fun signOut(view: View) {
        viewModel.signOut()
        startActivity(AuthActivity::class.java)
    }

}
