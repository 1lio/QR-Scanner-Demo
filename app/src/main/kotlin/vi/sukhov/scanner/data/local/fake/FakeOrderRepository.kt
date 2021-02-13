package vi.sukhov.scanner.data.local.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vi.sukhov.scanner.data.local.OrdersDatabase
import vi.sukhov.scanner.entity.Order
import vi.sukhov.scanner.util.Utils.getCurrentDate
import javax.inject.Singleton

@Singleton
object FakeOrderRepository : OrdersDatabase {

    private val fakeData = mutableListOf(
        Order(
            id = "0811300016",
            title = "Фурнитура  №1 FAKE",
            code = "0811300016AD",
            date = "08.12.20",
            status = "В листе ожидания",
            image = null
        ),
        Order(
            id = "1792310627",
            title = "Фурнитура  №2 FAKE",
            code = "1792310627DA",
            date = "12.12.21",
            status = "На складе",
            image = null
        ),
        Order(
            id = "6278310627",
            title = "Фурнитура  №3 FAKE",
            code = "1792310627DA",
            date = "18.12.21",
            status = "На складе",
            image = null
        )
    )

    private val notExistOrder = Order(
        id = "-1",
        title = "Нет в базе",
        code = null,
        date = null,
        status = "Данного продукта не сущ. в базе",
        image = null
    )

    override fun getOrder(id: String): Order {
        var order = notExistOrder.apply {
            this.id = id
        }

        fakeData.forEach {
            if (it.id == id) order = it
        }
        return order
    }

    override suspend fun addOrder(order: Order) {
        if (order.id.isNullOrEmpty() || order.id == "-1") order.id = order.code
        fakeData.add(order)
    }

    override suspend fun updateOrder(order: Order) {
        fakeData.remove(order)
        fakeData.add(order)
    }

    override suspend fun removeOrder(order: Order) {
        fakeData.remove(order)
    }

    override fun removeAllOrders() {
        fakeData.forEach {
            fakeData.remove(it)
        }
    }

    override fun getOrderListFlow(): Flow<List<Order>> {
        return flow {
            emit(fakeData.toList())
        }
    }

    override fun getOrderList(): List<Order> {
        return fakeData.toList()
    }
}