package vi.sukhov.scanner.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import vi.sukhov.scanner.data.gateway.OrdersStorage
import vi.sukhov.scanner.entity.Order
import java.util.*

object FirebaseOrderDatabase : OrdersStorage {

    private const val ORDERS_REFERENCE = "orders"

    private val repository = Firebase.database
    private val orderRef = repository.getReference(ORDERS_REFERENCE)

    private val notExistOrder = Order(
        id = "-1",
        title = "Нет в базе",
        code = null,
        date = null,
        status = "Данного продукта не сущ. в базе",
        image = null
    )

    @ExperimentalCoroutinesApi
    override suspend fun getOrder(id: String): Flow<Order> = callbackFlow {


        val re = Regex("[.,#$:// \\[\\]]")          // Firebase запрешает использовать данные символы
        val pId: String = re.replace(id, "")

        val eventListener =
            orderRef.child(pId) // Данные символы запрещены в firebase
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.let {

                            // Проблема с приведением типов. Упроно думает что string это лонг несмотря на кавычки в БД.
                            // Решено пока так.

                            val order = Order(
                                id = it.key ?: notExistOrder.id,
                                title = it.child("title").getValue(String::class.java)
                                    ?: notExistOrder.title,
                                code = it.child("code").getValue(String::class.java)
                                    ?: notExistOrder.code,
                                date = it.child("date").getValue(String::class.java)
                                    ?: notExistOrder.date,
                                status = it.child("status").getValue(String::class.java)
                                    ?: notExistOrder.status,
                                image = it.child("image").getValue(String::class.java)
                                    ?: notExistOrder.image,
                            )

                            this@callbackFlow.sendBlocking(order)
                        }
                    }

                    override fun onCancelled(snapshot: DatabaseError) {
                        this@callbackFlow.close(snapshot.toException())
                    }
                })

        awaitClose {
            orderRef.removeEventListener(eventListener)
        }

    }

    override suspend fun addOrder(order: Order) {
        val id =
            if (order.id != null && order.id != "-1") order.id else UUID.randomUUID().toString()
        repository.getReference(ORDERS_REFERENCE).child(id!!).setValue(order)
    }

    override suspend fun updateOrder(order: Order) {
        val id = order.id
        if (id != null) repository.getReference(ORDERS_REFERENCE).child(id).setValue(order)

    }

    @ExperimentalCoroutinesApi
    override suspend fun removeOrder(order: Order) {
        val id = order.id
        if (id != null) repository.getReference(ORDERS_REFERENCE).child(id).removeValue()
    }

    override fun removeAllOrders() {
        // Такую функцию лучше не делать)
    }

    @ExperimentalCoroutinesApi
    override fun getOrderListFlow(): Flow<List<Order>> = callbackFlow {

        val eventListener = orderRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.let {

                    val list: ArrayList<Order> = arrayListOf()
                    for (s: DataSnapshot in snapshot.children) {

                        // Проблема с приведением типов. Упроно думает что string это лонг несмотря на кавычки в БД.
                        // Решено пока так.
                        val order = Order(
                            id = s.key ?: notExistOrder.id,
                            title = s.child("title").getValue(String::class.java)
                                ?: notExistOrder.title,
                            code = s.child("code").getValue(String::class.java)
                                ?: notExistOrder.code,
                            date = s.child("date").getValue(String::class.java)
                                ?: notExistOrder.date,
                            status = s.child("status").getValue(String::class.java)
                                ?: notExistOrder.status,
                            image = s.child("image").getValue(String::class.java)
                                ?: notExistOrder.image,
                        )

                        list.add(order)
                    }
                    this@callbackFlow.sendBlocking(list)
                }
            }

            override fun onCancelled(snapshot: DatabaseError) {
                this@callbackFlow.close(snapshot.toException())
            }
        })

        awaitClose {
            orderRef.removeEventListener(eventListener)
        }
    }

    // Не используется
    override fun getOrderList(): List<Order> = emptyList()
}