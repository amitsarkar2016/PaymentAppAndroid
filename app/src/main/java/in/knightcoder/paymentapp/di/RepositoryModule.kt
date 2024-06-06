package `in`.knightcoder.paymentapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.knightcoder.paymentapp.data.remote.ApiService
import `in`.knightcoder.paymentapp.data.repository.UserRepository
import `in`.knightcoder.paymentapp.utils.SharePrefManager
import javax.inject.Singleton

/**
 * @Author: Amit Sarkar
 * @Date: 06-06-2024
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        apiService: ApiService,
        prefManager: SharePrefManager,
    ): UserRepository {
        return UserRepository(apiService, prefManager)
    }
}