package vi.sukhov.scanner.ui.home.orders

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import vi.sukhov.scanner.R
import vi.sukhov.scanner.core.common.BaseFragment
import vi.sukhov.scanner.data.local.fake.FakeOrderRepository
import vi.sukhov.scanner.databinding.FragmentOrderDetailBinding
import vi.sukhov.scanner.ui.home.HomeActivity
import vi.sukhov.scanner.util.Constants.IN_ORDER_ARG

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment(R.layout.fragment_order_detail) {

    private val binding by viewBinding(FragmentOrderDetailBinding::bind)
    private val navController: NavController by lazy {
        Navigation.findNavController(activity as HomeActivity, R.id.homeNavHostFragment)
    }

    private var idOrder = ""

    private val repoTmp = FakeOrderRepository()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idOrder = arguments?.getString(IN_ORDER_ARG) ?: ""

        GlobalScope.launch(Dispatchers.Main) { bindViews() }
    }

    private suspend fun bindViews() {
        val order = repoTmp.getOrder(idOrder)

        binding.titleDetailOrder.text = order.title
        binding.codeDetailOrder.text = order.code
        binding.statusDetailOrder.text = order.status
        binding.dateDetailOrder.text = order.date

        binding.buttonMoveToWaitList.setOnClickListener {
            lifecycleScope.launch {
                order.status = getString(R.string.in_wait_list)
                repoTmp.updateOrder(order)
            }
        }

        binding.buttonConfirm.setOnClickListener {
            lifecycleScope.launch {
                order.status = getString(R.string.in_warehouse)
                repoTmp.updateOrder(order)
            }
        }

        binding.buttonRemove.setOnClickListener {
            lifecycleScope.launch {
                repoTmp.removeOrder(order.id ?: "")
                navController.navigate(R.id.action_navigation_order_detail_to_navigation_orders)
            }
        }

    }
}