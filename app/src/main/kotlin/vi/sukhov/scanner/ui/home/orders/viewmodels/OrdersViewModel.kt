package vi.sukhov.scanner.ui.home.orders.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import vi.sukhov.scanner.data.local.OrdersDatabase
import vi.sukhov.scanner.entity.Order
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(repository: OrdersDatabase) : ViewModel() {

    private val _flowListOrders: MutableStateFlow<List<Order>> =
        MutableStateFlow(repository.getOrderList())

    val listOrders: StateFlow<List<Order>> = _flowListOrders

}
