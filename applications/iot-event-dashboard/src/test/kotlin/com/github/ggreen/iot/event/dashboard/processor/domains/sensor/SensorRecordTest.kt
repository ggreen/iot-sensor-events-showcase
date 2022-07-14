package com.github.ggreen.iot.event.dashboard.domains.sensor

import com.fasterxml.jackson.databind.ObjectMapper
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator
import org.junit.jupiter.api.Test

internal class SensorRecordTest{

    @Test
    internal fun json() {
        var objectMapper = ObjectMapper()
        var subject = JavaBeanGeneratorCreator.of(SensorOverview::class.java).create()

        subject.sensor?.sensorLocation?.latitude   = 35.746512259918504
        subject.sensor?.sensorLocation?.longitude = -94.39453125000001

        var json = objectMapper.writeValueAsString(subject)

        println(json)

    }
}