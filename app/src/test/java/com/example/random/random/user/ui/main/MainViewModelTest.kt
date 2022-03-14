package com.example.random.random.user.ui.main

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.random.random.user.network.repoimpl.RandomUserRepoImpl
import com.example.random.random.user.public.TestCall
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModelTest: MainViewModel
    private val testApp = mockk<Application>(relaxed = true)
    private val testRepo = mockk<RandomUserRepoImpl>(relaxed = true)
    private val testDispatchers = mockk<Dispatchers>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        mainViewModelTest =
            MainViewModel(
                application = testApp,
                dispatcher = testDispatchers,
                repo = testRepo
            )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun getUserShouldReturnSuccessfully() = runBlockingTest {
        coEvery { testRepo.fetchUser() } returns TestCall.createSuccessfulTestCallUserResponse()

        mainViewModelTest.getUser()

        assertEquals(
            TestCall.createSuccessfulTestCallUserResponse().data?.results?.get(0)?.name?.first, mainViewModelTest.randomUserFeed.value?.results?.get(0)?.name?.first
        )
        assertEquals(
            TestCall.createSuccessfulTestCallUserResponse().data?.results?.get(0)?.name?.last, mainViewModelTest.randomUserFeed.value?.results?.get(0)?.name?.last
        )
        assertEquals(
            TestCall.createSuccessfulTestCallUserResponse().data?.results?.get(0)?.name?.title, mainViewModelTest.randomUserFeed.value?.results?.get(0)?.name?.title
        )
    }

    @Test
    fun getUsersShouldReturnSuccessfully() = runBlockingTest {
        coEvery { testRepo.fetchUsers() } returns TestCall.createSuccessfulTestCallUsersResponse()

        mainViewModelTest.getUsers()

        assertEquals(
            5, mainViewModelTest.randomUsersFeed.value?.results?.size
        )
    }

    @Test
    fun updateUserShouldUpdateSuccessfully() = runBlockingTest {
        coEvery { testRepo.fetchUser() } returns TestCall.createSuccessfulTestCallUserResponse()

        mainViewModelTest.getUser()
        val testUser = TestCall.createUserResult()
        mainViewModelTest.updateUser(testUser)

        assertEquals(
            testUser.results.get(0).name?.first, mainViewModelTest.randomUserFeed.value?.results?.get(0)?.name?.first
        )

    }

    @Test
    fun saveUserShouldRemoveSensitiveData() = runBlockingTest {
        coEvery { testRepo.fetchUser() } returns TestCall.createSuccessfulTestCallUserResponse()

        mainViewModelTest.getUser()
        val testUser = mainViewModelTest.saveUser()

        assertEquals(
            testUser?.results?.get(0)?.login?.password, mainViewModelTest.randomUserFeed.value?.results?.get(0)?.login?.password
        )
    }
}
