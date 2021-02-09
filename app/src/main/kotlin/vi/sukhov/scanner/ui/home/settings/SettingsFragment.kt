package vi.sukhov.scanner.ui.home.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentSettingsBinding
import vi.sukhov.scanner.ui.auth.AuthActivity
import vi.sukhov.scanner.util.ThemeHelper
import vi.sukhov.scanner.util.ThemeMode
import vi.sukhov.scanner.util.extensions.doOnChange
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    @Inject lateinit var firebaseAuth: FirebaseAuth

    private val viewModel: SettingsViewModel by viewModels()
    private val binding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsDarkModeSwitch.isChecked = viewModel.isDarkMode.value ?: false

        binding.settingsDarkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.inThemeChanged(isChecked)
        }

        binding.signOut.setOnClickListener {
            firebaseAuth.signOut()
            Intent(requireContext(), AuthActivity::class.java).apply {
                putExtra("status", "User LoggedIN")
                startActivity(this)
                activity?.finish()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isDarkMode.doOnChange(this) {
            Timber.d("On Theme changed")
            ThemeHelper.applyTheme(if (it) ThemeMode.Dark else ThemeMode.Light)
        }
    }

}