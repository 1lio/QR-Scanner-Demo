package vi.sukhov.scanner.ui.home.scanner

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentScannerBinding

class ScannerFragment : BaseFragment(R.layout.fragment_scanner) {

    private val binding by viewBinding(FragmentScannerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}