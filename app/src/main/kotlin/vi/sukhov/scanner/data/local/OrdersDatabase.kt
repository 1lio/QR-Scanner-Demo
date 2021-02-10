package vi.sukhov.scanner.data.local

import kotlinx.coroutines.flow.Flow
import vi.sukhov.scanner.entity.Order

interface OrdersDatabase {

    suspend fun getOrder(id: String): Order

    suspend fun addOrder(order: Order)

    suspend fun updateOrder(order: Order)

    suspend fun removeOrder(id: String)

    fun removeAllOrders()

    // flow
    fun getOrderListFlow(): Flow<List<Order>>

    // sync
    fun getOrderList(): List<Order>
}