package vi.sukhov.scanner.ui.home.scanner

import android.graphics.PointF
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentScannerBinding
import vi.sukhov.scanner.ui.home.HomeActivity
import vi.sukhov.scanner.ui.home.orders.ClickOrderListener

class ScannerFragment : BaseFragment(R.layout.fragment_scanner),
    QRCodeReaderView.OnQRCodeReadListener {

    private val binding by viewBinding(FragmentScannerBinding::bind)
    private var idOrder = ""

    private val navController by lazy {
        Navigation.findNavController(
            context as HomeActivity,
            R.id.homeNavHostFragment
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCamera()
        binding.resultTextView.setOnClickListener {
            val bundle = bundleOf("idOrder" to idOrder)
            navController.navigate(
                R.id.action_navigation_scanner_to_navigation_order_detail,
                bundle
            )
        }
    }

    private fun initCamera() {
        binding.qrDecoder.setAutofocusInterval(1000L)
        binding.qrDecoder.setOnQRCodeReadListener(this)
        binding.qrDecoder.setBackCamera()
        binding.flashlightCheckbox.setOnCheckedChangeListener { _, isChecked ->
            binding.qrDecoder.setTorchEnabled(
                isChecked
            )
        }
        binding.enableDecodingCheckbox.setOnCheckedChangeListener { _, isChecked ->
            binding.qrDecoder.setQRDecodingEnabled(isChecked)
            if (!isChecked) binding.pointsOverlayView.clear()
        }
        binding.qrDecoder.startCamera()
    }

    override fun onResume() {
        super.onResume()
        binding.qrDecoder.startCamera()
    }

    override fun onPause() {
        super.onPause()
        binding.qrDecoder.stopCamera()
    }

    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed
    override fun onQRCodeRead(text: String?, points: Array<PointF>) {
        idOrder = text ?: ""
        binding.resultTextView.setOrderId(text ?: "0")
        binding.resultTextView.setOrderId(idOrder)
        binding.pointsOverlayView.setPoints(points)
    }
}
