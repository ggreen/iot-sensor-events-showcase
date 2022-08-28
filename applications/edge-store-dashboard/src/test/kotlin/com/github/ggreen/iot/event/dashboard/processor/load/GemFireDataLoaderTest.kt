package com.github.ggreen.iot.event.dashboard.processor.load

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import org.apache.geode.cache.Region
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class GemFireDataLoaderTest{

    private lateinit var sensorOverviewRegion: Region<String, SensorOverview>
    private lateinit var subject: GemFireDataLoader

    @BeforeEach
    internal fun setUp() {
        sensorOverviewRegion = mock()
        subject = GemFireDataLoader(sensorOverviewRegion)
    }

    @Test
    internal fun when_run_Then_load_sensorOverviews() {

        val args = null
        subject.run(args)

        verify(sensorOverviewRegion, atLeastOnce())[any()] = any()
    }

    @Test
    internal fun given_regionData_exist_When_run_Then_do_not_put_records() {

        whenever(sensorOverviewRegion.containsKeyOnServer(any())).thenReturn(true)

        val args = null
        subject.run(args)

        verify(sensorOverviewRegion, never())[any()] = any()
    }
}