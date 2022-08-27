package com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import com.github.ggreen.iot.event.dashboard.repositories.SensorRepository
import org.apache.geode.cache.Region
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * @author Gregory Green
 */
@Component
class SensorGemFireRepository(
    @Qualifier("sensorOverview")
    private val sensorOverviewRegion: Region<String, SensorOverview>) : SensorRepository {

    override fun findSensorOverviews(): Iterable<SensorOverview> {
        val keys = sensorOverviewRegion.keySetOnServer()
        return sensorOverviewRegion.getAll(keys).values
    }


    override fun updateAllSensorOverviewAlarmCounts(alarmCount: Int, priority: Short): Int {

        val keys = sensorOverviewRegion.keySetOnServer()
        var sensorOverview : SensorOverview?
        var count = 0
        for (key in keys)
        {
            sensorOverview = sensorOverviewRegion[key]

            if(sensorOverview == null)
                continue

            count++

            sensorOverview!!.alarmCount = alarmCount.toLong()
            sensorOverview!!.priority = priority
        }

        return count
    }
}