package com.github.ggreen.iot.event.dashboard.services

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Test for SensorDataService
 * @author Gregory Green
 */
internal class SensorDataServiceTest{
    private  var subject: SensorDataService = SensorDataService()

    @Test
    internal fun findSensorOverviews() {
        var actual = subject.findSensorOverviews();

        assertThat(actual).isNotEmpty
    }
}