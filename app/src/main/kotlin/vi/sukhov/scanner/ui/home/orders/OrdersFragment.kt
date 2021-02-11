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

@AndroidEntryPoint
class OrdersFragment : BaseFragment(R.layout.fragment_order_list) {

    private val binding: FragmentOrderListBinding by viewBinding()
    private val viewModel: OrdersViewModel by viewModels()

    private val navController by lazy { findNavController(requireActivity(), R.id.homeNavHostFragment) }
    private val listAdapter by lazy { OrderListAdapter(onClickOrder()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerOrders.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        lifecycleScope.launchWhenStarted {
            viewModel.listOrders.collect {
                listAdapter.updateListAndNotify(it)
            }
        }
    }

    private fun onClickOrder(): ClickOrderListener {
        return object : ClickOrderListener {
            override fun onClick(id: String?) {

                val bundle = bundleOf("idOrder" to id)

                navController.navigate(
                    R.id.action_navigation_orders_to_navigation_order_detail,
                    bundle
                )
            }
        }
    }

}
