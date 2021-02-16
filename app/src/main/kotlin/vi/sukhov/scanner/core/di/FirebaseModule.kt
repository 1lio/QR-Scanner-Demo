package vi.sukhov.scanner.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vi.sukhov.scanner.data.gateway.ChatStorage
import vi.sukhov.scanner.data.gateway.OrdersStorage
import vi.sukhov.scanner.data.gateway.UsersStorage
import vi.sukhov.scanner.data.local.database.fake.FakeChatRepository
import vi.sukhov.scanner.data.remote.FirebaseChatDatabase
import vi.sukhov.scanner.data.remote.FirebaseOrderDatabase
import vi.sukhov.scanner.data.remote.FirebaseUsersDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    // Auth

    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth = Firebase.auth

    // RealtimeDatabase

    @Provides
    @Singleton
    fun provideOrdersDatabase(): OrdersStorage = FirebaseOrderDatabase

    @Provides
    @Singleton
    fun provideUsersDatabase(): UsersStorage = FirebaseUsersDatabase

    @Provides
    @Singleton
    fun provideChatDatabase(): ChatStorage = FakeChatRepository

}