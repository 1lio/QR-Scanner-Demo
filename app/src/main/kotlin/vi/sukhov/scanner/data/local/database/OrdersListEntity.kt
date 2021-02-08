package vi.sukhov.scanner.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders_list")
data class OrdersListEntity(
    @PrimaryKey val id: String,
    val title: String?,           // Name order or other
    val date: String?,
    val status: String?,
    val image: String? = null
)