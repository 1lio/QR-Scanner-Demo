package vi.sukhov.scanner.ui.auth

import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseActivity
import vi.sukhov.scanner.databinding.ActivityAuthBinding
import vi.sukhov.scanner.util.ThemeHelper
import vi.sukhov.scanner.util.ThemeMode

@AndroidEntryPoint
class AuthActivity : BaseActivity(R.layout.activity_auth) {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeHelper.applyTheme(if (viewModel.isDarkModeOn()) ThemeMode.Dark else ThemeMode.Light)
    }

}
