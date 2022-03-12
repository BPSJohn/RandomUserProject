package com.example.random.random.user.network.endpoint

import com.example.random.random.user.network.data.RandomUserResult
import retrofit2.Response
import retrofit2.http.GET

interface RandomUserEndPoints {
    @GET("/api")
    suspend fun getUser() : Response<RandomUserResult>

    @GET("/api?results=10")
    suspend fun getUsers() : Response<RandomUserResult>
}
