package com.github.ggreen.iot.event.dashboard.services

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview

interface ISensorDataService{
    fun findSensorOverviews(): Iterable<SensorOverview>
}