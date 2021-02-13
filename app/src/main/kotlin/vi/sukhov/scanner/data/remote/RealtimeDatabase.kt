package vi.sukhov.scanner.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vi.sukhov.scanner.data.gateway.OrdersStorage
import vi.sukhov.scanner.entity.Order

// Заглушка

class RealtimeDatabase : OrdersStorage {

    override fun getOrder(id: String): Order? {
        return null
    }

    override suspend fun addOrder(order: Order) {

    }

    override suspend fun updateOrder(order: Order) {
    }

    override suspend fun removeOrder(order: Order) {
    }

    override fun removeAllOrders() {
    }

    override fun getOrderListFlow(): Flow<List<Order>> {
        return flow { }
    }

    override fun getOrderList(): List<Order> {
        return listOf()
    }
}