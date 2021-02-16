package vi.sukhov.scanner.core.common

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.ui.AppSettingsViewModel

// Общий код для всех активити

@AndroidEntryPoint
abstract class BaseActivity(@LayoutRes layout: Int) : AppCompatActivity(layout) {

    val viewModel: AppSettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Наблюдаем за изменением темы
        observeModeTheme()
    }

    private fun applyTheme(theme: Int) {
        AppCompatDelegate.setDefaultNightMode(
            when (theme) {
                ThemeMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                ThemeMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }

    private fun observeModeTheme() {
        lifecycleScope.launch {
            viewModel.isDarkMode().collect {
                applyTheme(if (it) ThemeMode.DARK else ThemeMode.LIGHT)
            }
        }
    }

    fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, duration).show()
    }

    fun startActivity(requiredActivity: Class<*>) {
        Intent(this, requiredActivity).apply {
            startActivity(this)
            finish()
        }
    }

    // Тип темы сохраню как константу (мини оптимизация enum)
    private object ThemeMode {
        const val LIGHT = 0
        const val DARK = 1
    }
}
