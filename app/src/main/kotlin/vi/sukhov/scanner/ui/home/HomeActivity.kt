package vi.sukhov.scanner.ui.home

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseActivity
import vi.sukhov.scanner.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : BaseActivity(R.layout.activity_home) {

    private val binding by viewBinding(ActivityHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}
