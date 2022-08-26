package com.github.ggreen.iot.event.dashboard.repositories

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview

/**
 * Data access interface for sensor data
 * @author Gregory Green
 */
interface SensorRepository{
    /**
     * @return the collection of overviews
     */
    fun findSensorOverviews(): Iterable<SensorOverview>

    /**
     * @return the number of records updates
     */
    fun updateAllSensorOverviewAlarmCounts(alarmCount: Int,priority: Short): Int
}