package vi.sukhov.scanner.ui.home.scanner

import android.graphics.PointF
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentScannerBinding

class ScannerFragment : BaseFragment(R.layout.fragment_scanner),
    QRCodeReaderView.OnQRCodeReadListener {

    private val binding by viewBinding(FragmentScannerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCamera()

        onClickFlash()
        onClickScanner()
    }

    private var flagFlash = false
    private var counterFlash = 0
    private fun onClickFlash() {
        binding.flashlightCheck.setOnClickListener {
            counterFlash++
            flagFlash = counterFlash % 2 != 0
            if (flagFlash) {
                binding.flashlightCheck.setImageResource(R.drawable.ic_baseline_flash_off_24)
            } else {
                binding.flashlightCheck.setImageResource(R.drawable.ic_baseline_flash_on_24)
            }

            binding.qrDecoder.setTorchEnabled(
                flagFlash
            )
        }
    }

    private var flagScanner = true
    private var counterScanner = 0

    private fun onClickScanner() {
        binding.decodingCheck.setOnClickListener {
            counterScanner++
            flagScanner = counterScanner % 2 == 0
            if (flagScanner) {
                binding.decodingCheck.setImageResource(R.drawable.ic_qr_code_1)
            } else {
                binding.decodingCheck.setImageResource(R.drawable.ic_qr_code_2)
            }
            binding.qrDecoder.setQRDecodingEnabled(flagScanner)
            if (!flagScanner) binding.pointsOverlayView.clear()
        }
    }

    private fun initCamera() {
        binding.qrDecoder.apply {
            setAutofocusInterval(500L)
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
        binding.resultView.setOrderId(text ?: "0")
        binding.resultView.setOrderId( text ?: "")
        binding.pointsOverlayView.setPoints(points)
    }

}
