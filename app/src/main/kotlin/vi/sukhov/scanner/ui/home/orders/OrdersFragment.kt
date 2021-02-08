package vi.sukhov.scanner.ui.home.orders

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentOrderDetailBinding

@AndroidEntryPoint
class OrdersFragment : BaseFragment(R.layout.fragment_order_list) {

    private val binding by viewBinding(FragmentOrderDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
