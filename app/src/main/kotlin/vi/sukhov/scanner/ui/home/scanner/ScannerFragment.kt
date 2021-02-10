package vi.sukhov.scanner.ui.home.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentScannerBinding

class ScannerFragment : BaseFragment(R.layout.fragment_scanner),
    ActivityCompat.OnRequestPermissionsResultCallback, QRCodeReaderView.OnQRCodeReadListener {

    private val binding by viewBinding(FragmentScannerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestCameraPermission()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.qrdecoderview.startCamera()
    }

    override fun onPause() {
        super.onPause()
        binding.qrdecoderview.stopCamera()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode != PERMISSION_REQUEST_CAMERA) {
            return
        }
        if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            toast("Camera permission was granted.")
            initqrdecoderview()
        } else {
            toast("Camera permission request was denied.")
        }
    }


    // Called when a QR is decoded
    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed
    override fun onQRCodeRead(text: String?, points: Array<PointF>) {
        binding.resultTextView.text = text
        binding.pointsOverlayView.setPoints(points)
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.CAMERA
            )
        ) {

            toast("Camera access is required to display the camera preview.")
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CAMERA
            )

        } else {
            toast("Permission is not available. Requesting camera permission.")

            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    Manifest.permission.CAMERA
                ), PERMISSION_REQUEST_CAMERA
            )
        }
    }

    private fun initqrdecoderview() {
        binding.qrdecoderview.setAutofocusInterval(2000L)
        binding.qrdecoderview.setOnQRCodeReadListener(this)
        binding.qrdecoderview.setBackCamera()
        binding.flashlightCheckbox.setOnCheckedChangeListener { _, isChecked ->
            binding.qrdecoderview.setTorchEnabled(
                isChecked
            )
        }
        binding.enableDecodingCheckbox.setOnCheckedChangeListener { _, isChecked ->
            binding.qrdecoderview.setQRDecodingEnabled(
                isChecked
            )
        }
        binding.qrdecoderview.startCamera()
    }

    private companion object {
        const val PERMISSION_REQUEST_CAMERA = 0
    }
}
