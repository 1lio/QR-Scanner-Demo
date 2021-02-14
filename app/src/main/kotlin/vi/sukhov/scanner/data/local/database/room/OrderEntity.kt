package vi.sukhov.scanner.data.local.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import vi.sukhov.scanner.entity.Order

@Entity(tableName = "orders_list")
data class OrderEntity(
    @PrimaryKey val id: String,
    val title: String?,           // Name order or other
    val code: String?,
    val date: String?,
    val status: String?,
    val image: String? = null
) {

    fun OrderEntity.toOrder(): Order {
        return Order(id, title, code, date, status, image)
    }
}