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
        binding.settingsDarkModeSwitch.setOnCheckedChangeListener(::switchListener)
        binding.signOut.setOnClickListener(::signOut)
    }

    private fun switchListener(buttonView: CompoundButton, isChecked: Boolean) {
        lifecycleScope.launch {
            viewModel.saveDarkMode(isChecked)
        }
    }

    private fun startActivityAuth() {
        startActivity(AuthActivity::class.java)
    }

    private fun loadingSetting() {

        lifecycleScope.launch {
            viewModel.getDarkMode().collect {
                binding.settingsDarkModeSwitch.isChecked = it
            }
        }
    }

    private fun signOut(view: View) {
        viewModel.signOut()
        startActivityAuth()
    }

}
