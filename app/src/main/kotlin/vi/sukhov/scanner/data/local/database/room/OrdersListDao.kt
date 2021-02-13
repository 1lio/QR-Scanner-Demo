package vi.sukhov.scanner.data.local.database.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdersListDao {

    @Query("Select * from orders_list")
    fun getOrderListFlow(): Flow<List<OrdersListEntity>>

    @Query("Select * from orders_list where id = :id")
    suspend fun getOrder(id: String): OrdersListEntity

    //Inserts data. If row already exists, replace the row
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stockList: List<OrdersListEntity>)

    //Update the row
    @Update
    suspend fun updateOrder(order: OrdersListEntity): Int

    // Delete all rows
    @Query("Delete from orders_list where id = :id")
    suspend fun removeOrder(id: String)

    @Query("Delete from orders_list")
    fun removeAll()

}
