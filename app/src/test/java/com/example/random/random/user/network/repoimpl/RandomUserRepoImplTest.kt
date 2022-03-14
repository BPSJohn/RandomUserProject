package com.example.random.random.user.network.repoimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.random.random.user.network.endpoint.RandomUserEndPoints
import com.example.random.random.user.network.repoimpl.RandomUserRepoImpl
import com.example.random.random.user.public.TestCall
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomUserRepoImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var randomUserRepoImplTest: RandomUserRepoImpl
    private val testDispatchers = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatchers)
    private val testRetrofit = mockk<RandomUserEndPoints>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatchers)
        randomUserRepoImplTest =
            RandomUserRepoImpl(
                dispatcher = Dispatchers,
                retroObject = testRetrofit
            )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun randomUserRepoShouldReturnServiceResultSuccess() = runBlocking {
        coEvery { testRetrofit.getUser() } returns TestCall.createResponseTestCallRandomUserResult()

        val testUserResponse = randomUserRepoImplTest.fetchUser()

        assertEquals(
            randomUserRepoImplTest.fetchUser(),
            testUserResponse
        )
    }
}
