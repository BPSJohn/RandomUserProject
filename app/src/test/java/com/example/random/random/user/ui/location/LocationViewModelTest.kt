package com.example.random.random.user.ui.location

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.random.random.user.public.TestCall
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.Assert
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
class LocationViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var locationViewModelTest: LocationViewModel
    private val testApp = mockk<Application>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        locationViewModelTest =
            LocationViewModel(
                application = testApp
            )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun locationViewModelShouldHaveUser() = runBlockingTest {

        locationViewModelTest.user = TestCall.createUser()

        assertEquals(
            TestCall.createUser().name?.title, locationViewModelTest.user.name?.title
        )
        assertEquals(
            TestCall.createUser().name?.first, locationViewModelTest.user.name?.first
        )
        assertEquals(
            TestCall.createUser().name?.last, locationViewModelTest.user.name?.last
        )
        assertEquals(
            TestCall.createUser().email, locationViewModelTest.user.email
        )
    }

    @Test
    fun locationViewModelShouldReturnRandomUserLocation() = runBlockingTest {

        locationViewModelTest.user = TestCall.createUser()

        assertEquals(
            "Location of random user: (-25.25, -25.25)", locationViewModelTest.setRandomUserLocation()
        )
    }

    @Test
    fun locationViewModelShouldReturnUserLocation() = runBlockingTest {

        locationViewModelTest.userLongitude = -25.25
        locationViewModelTest.userLatitude = -25.25

        assertEquals(
            "Location of this phone: (-25.25, -25.25)", locationViewModelTest.setUserLocation()
        )
    }

    @Test
    fun locationViewModelShouldReturnDistance() = runBlockingTest {
        locationViewModelTest.user = TestCall.createUser()
        locationViewModelTest.userLongitude = 1.0
        locationViewModelTest.userLatitude = 1.0

        assertEquals(
            "4061 kilometers", locationViewModelTest.getDistance()
        )
    }
}
