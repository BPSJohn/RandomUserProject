package com.example.random.random.user.di.remote

import com.example.random.random.user.data.remote.endpoint.RandomUserEndPoints
import com.example.random.random.user.data.remote.repo.RandomUserRepo
import com.example.random.random.user.data.remote.repoimpl.RandomUserRepoImpl
import com.example.random.random.user.data.remote.retrofit.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RandomUserRemoteModule {
    private const val RandomUserBaseUrl = "https://randomuser.me/"

    @Singleton
    @Provides
    fun provideRandomUserRetrofit() : RandomUserEndPoints {
        return RetrofitFactory.retrofitProvider(
            RandomUserEndPoints::class.java,
            RandomUserBaseUrl
        )
    }

    @Singleton
    @Provides
    fun provideRandomUserRepo(
        dispatcher : Dispatchers,
        retroObject : RandomUserEndPoints
    ) : RandomUserRepo {
        return RandomUserRepoImpl(dispatcher, retroObject)
    }
}
