package vi.sukhov.scanner.ui.auth

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseActivity
import vi.sukhov.scanner.databinding.ActivityAuthBinding

@AndroidEntryPoint
class AuthActivity : BaseActivity(R.layout.activity_auth) {

    private val binding by viewBinding(ActivityAuthBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}
