package vi.sukhov.scanner.data.local.database.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import vi.sukhov.scanner.data.local.database.room.DB.TABLE_ORDERS
import vi.sukhov.scanner.data.local.database.room.models.OrderEntity

@Dao
interface OrdersListDao {

    @Query("Select * from $TABLE_ORDERS")
    fun getOrderListFlow(): Flow<List<OrderEntity>>

    @Query("Select * from $TABLE_ORDERS where id = :id")
    suspend fun getOrder(id: String): OrderEntity

    //Inserts data. If row already exists, replace the row
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stockList: List<OrderEntity>)

    @Update
    suspend fun updateOrder(order: OrderEntity): Int

    @Query("Delete from $TABLE_ORDERS where id = :id")
    suspend fun removeOrder(id: String)

    @Query("Delete from $TABLE_ORDERS")
    fun removeAll()

}
