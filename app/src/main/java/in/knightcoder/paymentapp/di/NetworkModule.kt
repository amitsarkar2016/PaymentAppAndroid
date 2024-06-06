package `in`.knightcoder.paymentapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import `in`.knightcoder.paymentapp.data.remote.ApiService
import `in`.knightcoder.paymentapp.data.remote.RetrofitHelper
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @Author: Amit Sarkar
 * @Date: 06-06-2024
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext context: Context, gson: Gson): Retrofit {
        return RetrofitHelper.getInstance(context)
    }



    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}