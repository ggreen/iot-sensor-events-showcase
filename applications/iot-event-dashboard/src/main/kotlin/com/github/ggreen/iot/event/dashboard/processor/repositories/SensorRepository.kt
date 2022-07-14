package com.github.ggreen.iot.event.dashboard.repositories

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview

interface SensorRepository{
    fun findSensorOverviews(): Iterable<SensorOverview>
}