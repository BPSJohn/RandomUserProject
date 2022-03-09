package com.example.random.random.user.public

import com.example.random.random.user.data.model.*
import com.example.random.random.user.data.remote.endpoint.RandomUserEndPoints
import com.example.random.random.user.data.remote.networkmodel.ServiceResult
import io.mockk.every
import io.mockk.mockk
import retrofit2.Response

object TestCall {
    fun createSuccessfulTestCallUserResponse(): ServiceResult.Success<RandomUserResult?> {
        return ServiceResult.Success(
            mockk<RandomUserResult>() {
                every { results } returns
                        listOf(
                            mockk<User>() {
                                every { name } returns mockk<Name>() {
                                            every { title } returns "Mr."
                                            every { first } returns "Bob"
                                            every { last } returns "Ross"
                                        }
                                every { email } returns "Bob@Ross.com"
                            }
                    )

            }
        )
    }

    fun createUser(): User {
       return mockk<User>() {
            every { name } returns mockk<Name>() {
                every { title } returns "Mr."
                every { first } returns "Bob"
                every { last } returns "Ross"
            }
            every { email } returns "Bob@Ross.com"
           every { location } returns mockk<Location>() {
               every { coordinates } returns mockk<Coordinates>() {
                   every { latitude } returns "-25.25"
                   every { longitude } returns "-25.25"
               }
           }
        }
    }

    fun createResponseTestCallRandomUserResult(): Response<RandomUserResult> {
        return mockk<Response<RandomUserResult>>(relaxed = true) {
            every { isSuccessful } returns true
            every { body() } returns
                    mockk<RandomUserResult>() {
                        every { results } returns
                                listOf(
                                    mockk<User>() {
                                        every { name } returns mockk<Name>() {
                                            every { title } returns "Mr."
                                            every { first } returns "Bob"
                                            every { last } returns "Ross"
                                        }
                                        every { email } returns "Bob@Ross.com"
                                    }
                                )
                    }
        }
    }
}
