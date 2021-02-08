package vi.sukhov.scanner.ui.home.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentSettingsBinding
import vi.sukhov.scanner.util.ThemeHelper
import vi.sukhov.scanner.util.ThemeMode
import vi.sukhov.scanner.util.extensions.doOnChange

@AndroidEntryPoint
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels()
    private val binding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsDarkModeSwitch.isChecked = viewModel.isDarkMode.value ?: false

        binding.settingsDarkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.inThemeChanged(isChecked)
        }

   /*     viewModel.isDarkMode.observe(viewLifecycleOwner, {
            Timber.d("On Theme changed")
            ThemeHelper.applyTheme(if (it) ThemeMode.Dark else ThemeMode.Light)
        })*/

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isDarkMode.doOnChange(this) {
            Timber.d("On Theme changed")
            ThemeHelper.applyTheme(if (it) ThemeMode.Dark else ThemeMode.Light)
        }
    }

}