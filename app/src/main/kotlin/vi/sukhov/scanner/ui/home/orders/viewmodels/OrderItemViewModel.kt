package vi.sukhov.scanner.ui.home.orders.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.gateway.OrdersStorage
import vi.sukhov.scanner.entity.Order
import javax.inject.Inject

@HiltViewModel
class OrderItemViewModel @Inject constructor(private val repository: OrdersStorage) : ViewModel() {

    private val _flowOrder: MutableStateFlow<Order?> = MutableStateFlow(null)
    val order: StateFlow<Order?> = _flowOrder

    fun setOrderId(id: String) {
        viewModelScope.launch {
            repository.getOrder(id).collect {
                _flowOrder.value = it
            }
        }
    }

    fun addOrder(order: Order) {
        viewModelScope.launch {
            repository.addOrder(order)
        }
    }

    fun updateOrder(order: Order) {
        viewModelScope.launch {
            repository.updateOrder(order)
        }
    }

    fun removeOrder(order: Order) {
        viewModelScope.launch {
            repository.removeOrder(order)
        }
    }

}