package vi.sukhov.scanner.core.common

import android.content.Intent
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    fun toast(msg: CharSequence, duration: Int = Toast.LENGTH_SHORT, show: Boolean = true): Toast {
        val toast = Toast.makeText(requireActivity(), msg, duration)
        if (show) toast.show()
        return toast
    }

    fun startActivity(requiredActivity: Class<*>) {
        Intent(requireContext(), requiredActivity).apply {
            startActivity(this)
            requireActivity().finish()
        }
    }
}