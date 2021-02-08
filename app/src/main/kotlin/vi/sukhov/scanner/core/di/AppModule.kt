package vi.sukhov.scanner.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import vi.sukhov.scanner.data.local.prefs.PreferenceStorage
import vi.sukhov.scanner.data.local.prefs.SharedPreferenceStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePreferenceStorage(@ApplicationContext context: Context): PreferenceStorage =
        SharedPreferenceStorage(context)
}