package vi.sukhov.scanner.ui.home.orders

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentOrderListBinding
import vi.sukhov.scanner.ui.home.orders.adapter.OrderListAdapter
import vi.sukhov.scanner.ui.home.orders.viewmodels.OrdersViewModel
import vi.sukhov.scanner.core.common.Constants.IN_ORDER_ARG

@AndroidEntryPoint
class OrderListFragment : BaseFragment(R.layout.fragment_order_list) {

    private val binding: FragmentOrderListBinding by viewBinding()
    private val viewModel: OrdersViewModel by viewModels()
    private val listAdapter by lazy { OrderListAdapter(onClickOrder()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        observeList()
    }

    private fun initRecycler() {
        binding.recyclerOrders.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    private fun observeList() {
        lifecycleScope.launchWhenStarted {
            viewModel.listOrders.collect { order ->

                val inWaitList = order.filter { it.status == getString(R.string.on_wait_list) }.size
                val inWarehouse =
                    order.filter { it.status == getString(R.string.on_warehouse) }.size

                binding.countInWarehouse.text = inWarehouse.toString()
                binding.countWaitList.text = inWaitList.toString()

                listAdapter.updateListAndNotify(order)
            }
        }
    }

    private fun onClickOrder(): ClickOrderListener = object : ClickOrderListener {
        override fun onClick(id: String?) {
            val bundle = bundleOf(IN_ORDER_ARG to id)
            findNavController(requireActivity(), R.id.homeNavHostFragment)
                .navigate(R.id.action_navigation_orders_to_navigation_order_detail, bundle)
        }
    }

}
