package com.github.ggreen.iot.event.dashboard.services

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator
import org.springframework.stereotype.Service

/**
 * @author Gregory Green
 */
class SensorDataService : ISensorDataService {
    override fun findSensorOverviews(): Iterable<SensorOverview> {

        var overview = JavaBeanGeneratorCreator.of(SensorOverview::class.java).create()
        //(39.592246, -92.875468
        overview.sensor?.sensorLocation?.latitude  = 39.592246
        overview.sensor?.sensorLocation?.longitude  = -92.875468

        return arrayListOf(overview)
    }
}