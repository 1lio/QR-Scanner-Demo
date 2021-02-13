package vi.sukhov.scanner.core.common

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireActivity(), msg, duration).show()
    }
}