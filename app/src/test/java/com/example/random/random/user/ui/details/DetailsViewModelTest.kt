package com.example.random.random.user.ui.details

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
class DetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailsViewModelTest: DetailsViewModel
    private val testApp = mockk<Application>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        detailsViewModelTest =
            DetailsViewModel(
                application = testApp
            )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun detailsViewModelShouldHaveUser() = runBlockingTest {

        detailsViewModelTest.user = TestCall.createUser()

        assertEquals(
            TestCall.createUser().name?.title, detailsViewModelTest.user.name?.title
        )
        assertEquals(
            TestCall.createUser().name?.first, detailsViewModelTest.user.name?.first
        )
        assertEquals(
            TestCall.createUser().name?.last, detailsViewModelTest.user.name?.last
        )
        assertEquals(
            TestCall.createUser().email, detailsViewModelTest.user.email
        )
    }
}
