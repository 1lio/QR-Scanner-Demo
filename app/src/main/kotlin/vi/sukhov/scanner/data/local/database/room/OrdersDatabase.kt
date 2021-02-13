package vi.sukhov.scanner.data.local.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import vi.sukhov.scanner.data.local.database.room.DB.DATABASE_NAME
import vi.sukhov.scanner.data.local.database.room.DB.DATABASE_VERSION

@Database(entities = [OrdersListEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class OrdersDatabase : RoomDatabase() {

    abstract fun ordersListDao(): OrdersListDao

    companion object {

        fun buildDatabase(context: Context): OrdersDatabase {
            return Room.databaseBuilder(context, OrdersDatabase::class.java, DATABASE_NAME).build()
        }
    }
}