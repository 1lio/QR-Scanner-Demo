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

    private fun onClickFlash() {
        binding.flashlightCheck.setOnClickListener {
            binding.flashlightCheck.onClick()

            val isChecked = binding.flashlightCheck.isChecked()
            val image = if (!isChecked) R.drawable.ic_flash_off else R.drawable.ic_flash_on

            binding.flashlightCheck.setImageResource(image)
            binding.qrDecoder.setTorchEnabled(!isChecked)
        }
    }

    private fun onClickScanner() {
        binding.decodingCheck.setOnClickListener {

            binding.decodingCheck.onClick()

            val isChecked = binding.decodingCheck.isChecked()
            val image = if (isChecked) R.drawable.ic_qr_code_1 else R.drawable.ic_qr_code_2

            binding.decodingCheck.setImageResource(image)
            binding.qrDecoder.setQRDecodingEnabled(isChecked)

            if (!isChecked) binding.pointsOverlayView.clear()
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
        binding.resultView.setOrderId(text ?: "")
        binding.pointsOverlayView.setPoints(points)
    }

}
