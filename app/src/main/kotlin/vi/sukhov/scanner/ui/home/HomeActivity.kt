package vi.sukhov.scanner.ui.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseActivity
import vi.sukhov.scanner.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : BaseActivity(R.layout.activity_home) {

    private val binding: ActivityHomeBinding by viewBinding()

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.navigation_scanner,
            R.id.navigation_orders,
            R.id.navigation_chat,
            R.id.navigation_settings
        )
    }

    private lateinit var navController: NavController
    private var navHostFragment: NavHostFragment? = null
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.homeNavHostFragment) as? NavHostFragment ?: return

        navController = findNavController(R.id.homeNavHostFragment)
        appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS)

        binding.homeBottomNavView.setupWithNavController(navController)
    }

}
