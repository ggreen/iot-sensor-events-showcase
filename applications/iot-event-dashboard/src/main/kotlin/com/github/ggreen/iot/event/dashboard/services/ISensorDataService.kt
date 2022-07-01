package com.github.ggreen.iot.event.dashboard.services

import com.github.ggreen.iot.event.dashboard.domains.SensorOverview

interface ISensorDataService{
    fun findSensorOverviews(): Iterable<SensorOverview>
}