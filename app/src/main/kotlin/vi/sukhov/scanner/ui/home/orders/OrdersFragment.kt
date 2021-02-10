package vi.sukhov.scanner.ui.home.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.databinding.FragmentOrderListBinding
import vi.sukhov.scanner.ui.home.HomeActivity
import vi.sukhov.scanner.ui.home.orders.adapter.ClickOrder
import vi.sukhov.scanner.ui.home.orders.adapter.OrderListAdapter

@AndroidEntryPoint
class OrdersFragment : BaseFragment(R.layout.fragment_order_list) {

    private val binding: FragmentOrderListBinding by viewBinding()
    private val viewModel: OrdersViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        navController = findNavController(activity as HomeActivity, R.id.homeNavHostFragment)
         //ordersAdapter.updateListAndNotify(it)
        return super.onCreateView(inflater, group, state)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerOrders.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = OrderListAdapter(onClickOrder())
        }

    }

    private fun onClickOrder(): ClickOrder {
        return object : ClickOrder {
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
