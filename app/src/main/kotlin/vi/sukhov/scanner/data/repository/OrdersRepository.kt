package vi.sukhov.scanner.data.repository

import kotlinx.coroutines.flow.Flow
import vi.sukhov.scanner.data.gateway.OrdersStorage
import vi.sukhov.scanner.entity.Order
import javax.inject.Inject

class OrdersRepository @Inject constructor(private val storage: OrdersStorage) : OrdersStorage {

    override suspend fun getOrder(id: String): Flow<Order> {
        return storage.getOrder(id)
    }

    override suspend fun addOrder(order: Order) {
        storage.addOrder(order)
    }

    override suspend fun updateOrder(order: Order) {
        storage.updateOrder(order)
    }

    override suspend fun removeOrder(order: Order) {
        storage.removeOrder(order)
    }

    override fun removeAllOrders() {
        storage.removeAllOrders()
    }

    override fun getOrderListFlow(): Flow<List<Order>> {
        return storage.getOrderListFlow()
    }

    override  fun getOrderList(): List<Order> {
        return storage.getOrderList()
    }

}