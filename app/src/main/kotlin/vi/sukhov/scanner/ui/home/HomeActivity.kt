package vi.sukhov.scanner.ui.home

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseActivity
import vi.sukhov.scanner.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : BaseActivity(R.layout.activity_home) {

    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.homeNavHostFragment) as NavHostFragment }
    private val navController by lazy { navHostFragment.findNavController() }
    private val binding: ActivityHomeBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.homeBottomNavView.setupWithNavController(navController)
        checkPermissions()
    }

    override fun onRequestPermissionsResult(code: Int, perm: Array<out String>, results: IntArray) {
        super.onRequestPermissionsResult(code, perm, results)

        if (code != PERMISSION_REQUEST_CAMERA) return

        if (results.size != 1 && results[0] != PERMISSION_GRANTED) {
            toast("Camera permission request was denied.")
        }
    }

    // Проверяем разрешения
    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, CAMERA) != PERMISSION_GRANTED) {
            requestCameraPermission()
        }
    }

    // Запрашиваем доступ к камере
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
    }

}
