package vi.sukhov.scanner.ui.home.orders.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import vi.sukhov.scanner.data.local.OrdersDatabase
import vi.sukhov.scanner.entity.Order
import vi.sukhov.scanner.ui.auth.AuthViewModel
import javax.inject.Inject

@HiltViewModel
class OrderItemViewModel @Inject constructor(private val repository: OrdersDatabase) : ViewModel() {

    private val _flowOrder: MutableStateFlow<Order?> = MutableStateFlow(null)
    val order: StateFlow<Order?> = _flowOrder

    fun setOrderId(id: String) {
        _flowOrder.value = repository.getOrder(id)
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