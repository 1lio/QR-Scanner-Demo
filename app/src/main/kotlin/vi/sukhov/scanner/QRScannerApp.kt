package vi.sukhov.scanner

import android.app.Application
import androidx.room.RoomDatabase
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.HiltAndroidApp
import vi.sukhov.scanner.data.local.database.room.OrdersDatabase

@HiltAndroidApp
class QRScannerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startRoom()
        startFirebaseDatabase()
    }


    private fun startRoom() {
        OrdersDatabase.buildDatabase(applicationContext)
    }

    private fun startFirebaseDatabase() {
        val database = FirebaseDatabase.getInstance()
        database.setPersistenceEnabled(true)            // Кэширование db
    }
}