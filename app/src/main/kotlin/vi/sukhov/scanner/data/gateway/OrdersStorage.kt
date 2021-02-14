package vi.sukhov.scanner.data.gateway

import kotlinx.coroutines.flow.Flow
import vi.sukhov.scanner.entity.Order

interface OrdersStorage {

    suspend fun getOrder(id: String): Flow<Order>

    suspend fun addOrder(order: Order)

    suspend fun updateOrder(order: Order)

    suspend fun removeOrder(order: Order)

    fun removeAllOrders()

    fun getOrderListFlow(): Flow<List<Order>>

    @Deprecated("[sync] Used only in local")
    fun getOrderList(): List<Order>
}