package vi.sukhov.scanner.ui.home

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
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

    private lateinit var navHost: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navHost = supportFragmentManager.findFragmentById(R.id.homeNavHostFragment) as NavHostFragment
        navController = findNavController(R.id.homeNavHostFragment)
        appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS)

        binding.homeBottomNavView.setupWithNavController(navController)

        if (ActivityCompat.checkSelfPermission(this,CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission()
        }
    }

    override fun onRequestPermissionsResult(code: Int, permissions: Array<out String>, results: IntArray) {
        super.onRequestPermissionsResult(code, permissions, results)
        if (code != PERMISSION_REQUEST_CAMERA) return

        if (results.size != 1 && results[0] != PackageManager.PERMISSION_GRANTED) {
            toast("Camera permission request was denied.")
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA)) {
            toast("Camera access is required to display the camera preview.")
            ActivityCompat.requestPermissions(this, arrayOf(CAMERA), PERMISSION_REQUEST_CAMERA)
        } else {
            toast("Permission is not available. Requesting camera permission.")
            ActivityCompat.requestPermissions(this, arrayOf(CAMERA), PERMISSION_REQUEST_CAMERA)
        }
    }

    private companion object {
         const val PERMISSION_REQUEST_CAMERA = 0
         val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.navigation_scanner,
            R.id.navigation_orders,
            R.id.navigation_chat,
            R.id.navigation_settings
        )
    }
}
