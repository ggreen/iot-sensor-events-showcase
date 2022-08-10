package com.github.ggreen.iot.event.dashboard.processor.controllers

import com.github.ggreen.iot.event.dashboard.repositories.SensorRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


internal class ClearAlertsControllerTest{

    private lateinit var repository: SensorRepository

    @BeforeEach
    internal fun setUp() {
        repository = mock()


    }

    @Test
    internal fun whenClearThenRecordsUpdated() {
        val expected = 3

        whenever(repository.updateAllSensorOverviewAlarmCounts(any<Int>(),any<Short>())).thenReturn(expected)

        var subject = ClearAlertsController(repository)
        var actual = subject.clearAlerts()
        assertEquals(expected,actual);

    }
}