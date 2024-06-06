package `in`.knightcoder.paymentapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import `in`.knightcoder.paymentapp.utils.SharePrefManager
import javax.inject.Singleton

/**
 * @Author: Amit Sarkar
 * @Date: 06-06-2024
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharePrefManager(@ApplicationContext context: Context): SharePrefManager {
        return SharePrefManager.getPrefInstance(context)
    }
}