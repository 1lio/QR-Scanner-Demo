package vi.sukhov.scanner.ui.auth

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseActivity
import vi.sukhov.scanner.ui.home.HomeActivity

// Данная активити отвечает за работу с авторизацией

@AndroidEntryPoint
class AuthActivity : BaseActivity(R.layout.activity_auth) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Чекаем авторизован ли пользователь
        lifecycleScope.launch {
            viewModel.isSigned().collect {
                if (it) startActivity(HomeActivity::class.java)
            }
        }
    }

}