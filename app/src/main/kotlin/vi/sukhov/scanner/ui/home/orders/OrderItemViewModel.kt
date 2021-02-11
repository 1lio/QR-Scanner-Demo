package vi.sukhov.scanner.ui.home.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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

}