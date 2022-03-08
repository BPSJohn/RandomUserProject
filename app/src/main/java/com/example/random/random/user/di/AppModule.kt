package com.example.random.random.user.di

import com.example.random.random.user.data.remote.retrofit.RetrofitFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutines() = Dispatchers

    @Singleton
    @Provides
    fun provideRetrofitFactory() = RetrofitFactory

    @Singleton
    @Provides
    fun provideGson() : Gson {
        return Gson()
    }
}
