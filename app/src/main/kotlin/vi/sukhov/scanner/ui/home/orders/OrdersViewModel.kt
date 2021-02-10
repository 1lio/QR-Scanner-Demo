package vi.sukhov.scanner.ui.home.orders

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.local.OrdersDatabase
import vi.sukhov.scanner.entity.Order
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(repository: OrdersDatabase) {

    private var _list: List<Order> = arrayListOf()

    init {
        GlobalScope.launch {
            repository.getOrderListFlow().collect {
                _list = it
            }
        }
    }

    private val _flowListOrders: MutableStateFlow<List<Order>> = MutableStateFlow(_list)


    fun observeListOrder(){

    }
}