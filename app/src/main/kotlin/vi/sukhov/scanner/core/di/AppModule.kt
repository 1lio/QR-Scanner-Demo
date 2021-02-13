package vi.sukhov.scanner.core.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import vi.sukhov.scanner.data.gateway.OrdersStorage
import vi.sukhov.scanner.data.local.database.fake.FakeOrderRepository
import vi.sukhov.scanner.data.gateway.PreferenceStorage
import vi.sukhov.scanner.data.local.prefs.DataStoreStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideStorage(@ApplicationContext context: Context): PreferenceStorage =
        DataStoreStorage(context)          // Так ща модно)
       // SharedPreferenceStorage(context) // По старинке

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideOrdersDatabase(): OrdersStorage = FakeOrderRepository
}