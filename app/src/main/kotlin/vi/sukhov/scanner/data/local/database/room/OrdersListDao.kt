package vi.sukhov.scanner.data.local.database.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdersListDao {

    @Query("Select * from orders_list")
    fun getOrderListFlow(): Flow<List<OrderEntity>>

    @Query("Select * from orders_list where id = :id")
    suspend fun getOrder(id: String): OrderEntity

    //Inserts data. If row already exists, replace the row
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stockList: List<OrderEntity>)

    @Update
    suspend fun updateOrder(order: OrderEntity): Int

    @Query("Delete from orders_list where id = :id")
    suspend fun removeOrder(id: String)

    @Query("Delete from orders_list")
    fun removeAll()

}
