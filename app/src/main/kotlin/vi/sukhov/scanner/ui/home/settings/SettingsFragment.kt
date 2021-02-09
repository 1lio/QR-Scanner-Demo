package vi.sukhov.scanner.ui.home.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentSettingsBinding
import vi.sukhov.scanner.ui.auth.AuthActivity

// Основыные настройки приложения

@AndroidEntryPoint
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels()
    private val binding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingSetting()    // Загружаем графическое отражение настроек

        // Устанавливаем обработчики
        binding.settingsDarkModeSwitch.setOnCheckedChangeListener(::switchListener)
        binding.signOut.setOnClickListener(::signOut)
    }

    private fun switchListener(buttonView: CompoundButton, isChecked: Boolean) {
        viewModel.inThemeChanged(isChecked)
    }

    private fun startActivityAuth() {
        Intent(requireContext(), AuthActivity::class.java).apply {
            startActivity(this)
            activity?.finish()
        }
    }

    private fun loadingSetting() {
        binding.settingsDarkModeSwitch.isChecked = viewModel.isDarkMode.value ?: false
    }

    private fun signOut(view: View) {
        viewModel.signOut()
        startActivityAuth()
    }

}
