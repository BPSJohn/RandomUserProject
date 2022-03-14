package com.example.random.random.user.utils

import io.mockk.unmockkAll
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

class DistanceHelperTest {

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun distanceHelperShouldReturnCorrectDistance() = runBlockingTest {

        val distanceHelper = DistanceHelper()

        assertEquals(
            786, distanceHelper.distance(5.0, 5.0, 0.0, 0.0, 'K')
        )
    }
}
