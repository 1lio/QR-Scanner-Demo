package vi.sukhov.scanner.data.local.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import vi.sukhov.scanner.data.local.OrdersDatabase
import vi.sukhov.scanner.entity.Order
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Singleton
class FakeOrderRepository : OrdersDatabase {

    private val fakeData = mutableSetOf(
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
        )
    )

    private val currentDate: Date = Date()
    private val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    private val dateText: String = dateFormat.format(currentDate)

    private val notExistOrder = Order(
        id = "-1",
        title = "Нет в базе",
        code = null,
        date = dateText,
        status = "Данного продукта не сущ. в базе",
        image = null
    )

    override fun getOrder(id: String): Order {
        var order = notExistOrder
        fakeData.forEach {
            if (it.id == id) order = it
        }
        return order
    }

    override suspend fun addOrder(order: Order) {
        fakeData.add(order)
    }

    override suspend fun updateOrder(order: Order) {
        val id = order.id
        fakeData.forEach {
            if (it.id == id) {
                fakeData.remove(it)
                fakeData.add(order)
            }
        }
    }

    override suspend fun removeOrder(id: String) {
        fakeData.forEach {
            if (it.id == id) {
                fakeData.remove(it)
            }
        }
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