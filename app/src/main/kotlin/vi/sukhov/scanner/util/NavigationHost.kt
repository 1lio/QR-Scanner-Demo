package vi.sukhov.scanner.util

import androidx.appcompat.widget.Toolbar

interface NavigationHost {
    fun registerToolbarWithNavigation(toolbar: Toolbar)
}