package vi.sukhov.scanner.ui.home.orders

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment(R.layout.fragment_order_detail) {

    private val binding by viewBinding(FragmentOrderDetailBinding::bind)
    private lateinit var navController: NavController

    private var idOrder = ""

    private val repoTmp = FakeOrderRepository()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(activity as HomeActivity, R.id.homeNavHostFragment)
        idOrder = arguments?.getString("idOrder") ?: "not args"

        GlobalScope.launch(Dispatchers.Main) { bindViews() }
    }

    private suspend fun bindViews() {
        val order = repoTmp.getOrder(idOrder)

        binding.titleDetailOrder.text = order.title
        binding.codeDetailOrder.text = order.code
        binding.statusDetailOrder.text = order.status
        binding.dateDetailOrder.text = order.date

        binding.buttonMoveToWaitList.setOnClickListener {
            GlobalScope.launch {
                order.status = "В листе ожидания"
                repoTmp.updateOrder(order)
            }
        }

        binding.buttonConfirm.setOnClickListener {
            GlobalScope.launch {
                order.status = "Зачислено на склад"
                repoTmp.updateOrder(order)
            }
        }

        binding.buttonRemove.setOnClickListener {
            GlobalScope.launch {
                repoTmp.removeOrder(order.id ?: "")
                Toast.makeText(context, "Order has been deleted!", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_navigation_order_detail_to_navigation_orders)
            }
        }


    }
}