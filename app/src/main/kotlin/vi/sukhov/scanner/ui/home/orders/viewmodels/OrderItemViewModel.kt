package vi.sukhov.scanner.ui.home.orders.viewmodels

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import vi.sukhov.scanner.data.local.OrdersDatabase
import vi.sukhov.scanner.entity.Order
import javax.inject.Inject

@HiltViewModel
class OrderItemViewModel @Inject constructor(private val repository: OrdersDatabase) : ViewModel() {

    private val orderId = MutableLiveData<String>()

    var orderData: LiveData<Order> =
        Transformations.switchMap(orderId) {
            object : LiveData<Order>(repository.getOrder(it)) {}
        }

    fun setOrderId(crimeId: String) {
        orderId.value = crimeId
    }

    fun addToWareHouse(order: Order) {

        viewModelScope.launch {
            Log.i("JHJUYTHGBU", repository.getOrderList().toString())
            repository.addOrder(order)
            Log.i("JHJUYTHGBU",repository.getOrderList().toString())
        }

    }

}