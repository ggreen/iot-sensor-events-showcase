package com.github.ggreen.iot.event.dashboard.controllers

import com.github.ggreen.iot.event.dashboard.repositories.SensorRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class SensorOverviewSseControllerTest{

    private lateinit var sensorDataService : SensorRepository


    @BeforeEach
    internal fun setUp() {
        sensorDataService = mock()
    }

    @Test
    internal fun streamOverviewEvents() {
        var subject = SensorOverviewSseController(sensorDataService);
        subject.streamOverviewEvents()?.blockFirst()

        verify(sensorDataService).findSensorOverviews()
    }
}