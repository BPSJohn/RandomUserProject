package com.example.random.random.user.public

import com.example.random.random.user.network.data.*
import com.example.random.random.user.network.networkmodel.ServiceResult
import io.mockk.every
import io.mockk.mockk
import retrofit2.Response

object TestCall {
    fun createSuccessfulTestCallUserResponse(): ServiceResult.Success<RandomUserResult?> {
        return ServiceResult.Success(
            mockk<RandomUserResult>(relaxed = true) {
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

    fun createSuccessfulTestCallUsersResponse() : ServiceResult.Success<RandomUserResult?> {
        return ServiceResult.Success(
            mockk<RandomUserResult>(relaxed = true) {
                every { results } returns
                        listOf(
                            mockk<User>() {
                                every { name } returns mockk<Name>() {
                                    every { title } returns "Mr."
                                    every { first } returns "Bob"
                                    every { last } returns "Ross"
                                }
                                every { email } returns "Bob@Ross.com"
                            },
                            mockk<User>(relaxed = true),
                            mockk<User>(relaxed = true),
                            mockk<User>(relaxed = true),
                            mockk<User>(relaxed = true)
                        )
            }
        )
    }

    fun createUser(): User {
       return mockk<User>(relaxed = true) {
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

    fun createUserResult(): RandomUserResult {
        return mockk<RandomUserResult>(relaxed = true) {
            every { results } returns
                    listOf(
                        mockk<User>() {
                            every { name } returns mockk<Name>() {
                                every { title } returns "Mister"
                                every { first } returns "Billy"
                                every { last } returns "Joe"
                            }
                            every { email } returns "Billy@Joe.com"
                        }
                    )

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
