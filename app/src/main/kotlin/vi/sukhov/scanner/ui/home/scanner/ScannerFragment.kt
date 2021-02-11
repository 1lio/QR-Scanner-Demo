package vi.sukhov.scanner.ui.home.scanner

import android.graphics.PointF
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentScannerBinding
import vi.sukhov.scanner.ui.home.HomeActivity

class ScannerFragment : BaseFragment(R.layout.fragment_scanner),
    QRCodeReaderView.OnQRCodeReadListener {

    private val binding by viewBinding(FragmentScannerBinding::bind)
    private var idOrder = ""

    private val navController by lazy { Navigation.findNavController(context as HomeActivity, R.id.homeNavHostFragment) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCamera()

        binding.resultTextView.setOnClickListener {
            val bundle = bundleOf("idOrder" to idOrder)
            navController.navigate(R.id.action_navigation_scanner_to_navigation_order_detail, bundle)
        }

        onClickFlash()
        onClickScanner()

    }

    private var flagFlash = false
    private var counterFlash = 0
    private fun onClickFlash() {
        binding.flashlightCheckbox.setOnClickListener {
            counterFlash++
            flagFlash = counterFlash % 2 != 0
            if (flagFlash) {
                binding.flashlightCheckbox.setImageResource(R.drawable.ic_baseline_flash_off_24)
            } else {
                binding.flashlightCheckbox.setImageResource(R.drawable.ic_baseline_flash_on_24)
            }

            binding.qrDecoder.setTorchEnabled(
                flagFlash
            )
        }
    }

    private var flagScanner = true
    private var counterScanner = 0

    private fun onClickScanner() {
        binding.enableDecodingCheckbox.setOnClickListener {
            counterScanner++
            flagScanner = counterScanner % 2 == 0
            if (flagScanner) {
                binding.enableDecodingCheckbox.setImageResource(R.drawable.ic_qr_code_1)
            } else {
                binding.enableDecodingCheckbox.setImageResource(R.drawable.ic_qr_code_2)
            }
            binding.qrDecoder.setQRDecodingEnabled(flagScanner)
            if (!flagScanner) binding.pointsOverlayView.clear()
        }
    }

    private fun initCamera() {
        binding.qrDecoder.apply {
            setAutofocusInterval(1000L)
            setOnQRCodeReadListener(this@ScannerFragment)
            setBackCamera()
            startCamera()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.qrDecoder.startCamera()
    }

    override fun onPause() {
        super.onPause()
        binding.qrDecoder.stopCamera()
    }

    override fun onQRCodeRead(text: String?, points: Array<PointF>) {
        idOrder = text ?: ""
        binding.resultTextView.setOrderId(text ?: "0")
        binding.resultTextView.setOrderId(idOrder)
        binding.pointsOverlayView.setPoints(points)
    }

}
