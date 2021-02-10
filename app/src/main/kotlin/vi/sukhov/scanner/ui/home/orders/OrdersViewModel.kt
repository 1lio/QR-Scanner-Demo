package vi.sukhov.scanner.ui.home.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.local.OrdersDatabase
import vi.sukhov.scanner.entity.Order
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(repository: OrdersDatabase) : ViewModel() {

    private val _flowListOrders: MutableStateFlow<List<Order>> =
        MutableStateFlow(repository.getOrderList())

    val listOrders: StateFlow<List<Order>> = _flowListOrders

}
