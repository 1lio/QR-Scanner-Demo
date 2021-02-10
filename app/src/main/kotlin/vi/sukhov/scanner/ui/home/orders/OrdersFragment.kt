package vi.sukhov.scanner.ui.home.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.data.local.fake.FakeOrderRepository
import vi.sukhov.scanner.databinding.FragmentOrderListBinding
import vi.sukhov.scanner.ui.home.HomeActivity
import vi.sukhov.scanner.ui.home.orders.adapter.OrderListAdapter

@AndroidEntryPoint
class OrdersFragment : BaseFragment(R.layout.fragment_order_list) {

    private val binding: FragmentOrderListBinding by viewBinding()
    private lateinit var ordersAdapter: OrderListAdapter
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        navController = findNavController(activity as HomeActivity, R.id.homeNavHostFragment)
        ordersAdapter = OrderListAdapter(onClickOrder())

        GlobalScope.launch {
            FakeOrderRepository().getOrderListFlow().collect {
                ordersAdapter.updateListAndNotify(it)
            }
        }

        return super.onCreateView(inflater, group, state)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding.recyclerOrders

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = ordersAdapter
    }

    private fun onClickOrder(): View.OnClickListener {
        return View.OnClickListener {
            navController.navigate(R.id.action_navigation_orders_to_navigation_order_detail)
        }
    }

}
