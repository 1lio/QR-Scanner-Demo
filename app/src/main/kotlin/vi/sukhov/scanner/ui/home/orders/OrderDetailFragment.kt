package vi.sukhov.scanner.ui.home.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentOrderDetailBinding
import vi.sukhov.scanner.ui.home.HomeActivity
import vi.sukhov.scanner.ui.home.orders.viewmodels.OrderItemViewModel
import vi.sukhov.scanner.util.Constants.IN_ORDER_ARG

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment(R.layout.fragment_order_detail) {

    private val binding: FragmentOrderDetailBinding by viewBinding()
    private val viewModel: OrderItemViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setOrderId(arguments?.getString(IN_ORDER_ARG) ?: "")

        observeOrder()
    }

    private fun observeOrder() {

        lifecycleScope.launch {
            viewModel.order.collect { order ->

                if (order != null) {

                    binding.titleDetailOrder.text = order.title
                    binding.codeDetailOrder.text = order.code
                    binding.statusDetailOrder.text = order.status
                    binding.dateDetailOrder.text = order.date

                    binding.buttonMoveToWaitList.setOnClickListener {
                        binding.statusDetailOrder.text = getString(R.string.on_wait_list)
                        viewModel.updateOrder(
                            order.apply { status = getString(R.string.on_wait_list) })
                    }

                    binding.buttonConfirm.setOnClickListener {
                        binding.statusDetailOrder.text = getString(R.string.on_warehouse)
                        viewModel.updateOrder(
                            order.apply { status = getString(R.string.on_warehouse) })
                    }

                    binding.buttonRemove.setOnClickListener {
                        viewModel.removeOrder(order)
                        moveToOrdersList()
                    }
                }
            }
        }

    }

    private fun moveToOrdersList() {
        Navigation.findNavController(activity as HomeActivity, R.id.homeNavHostFragment)
            .navigate(R.id.action_navigation_order_detail_to_navigation_orders)
    }
}