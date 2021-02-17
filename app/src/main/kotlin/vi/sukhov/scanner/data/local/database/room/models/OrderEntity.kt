package vi.sukhov.scanner.data.local.database.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import vi.sukhov.scanner.data.local.database.room.DB.TABLE_ORDERS

@Entity(tableName = TABLE_ORDERS)
data class OrderEntity(
    @PrimaryKey val id: String,
    val title: String?,
    val code: String?,
    val date: String?,
    val status: String?,
    val image: String? = null
)