package com.github.ggreen.rabbit.gemfire.sensor.analyzer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import org.apache.geode.cache.Region
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.function.Consumer

/**
 * @author Gregory Green
 */
@Component
class UpdateSensorOverviewConsumer(private val sensorOverviewRegion: Region<String, SensorOverview>,
                                   @Value("\${sensor.update.isAlarm}")
                                   private val updateAlarm: Boolean,
                                   @Value("\${sensor.update.idField:id}")
                                   private val idField: String,
                                   @Value("\${sensor.alarm.severe.threshold:10}")
                                   private val alarmSevereThreshold : Int = 10,
                                   @Value("\${sensor.warning.threshold:4}")
                                   private val warningThreshold : Int = 4) : Consumer<ByteArray> {

    private val warningPriority: Short = 1
    private val severePriority: Short = 2

    private val logger: Logger = LoggerFactory.getLogger("UpdateSensorOverviewConsumer")
    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun accept(payload: ByteArray) {
        val payloadMap = objectMapper.readTree(payload)

        val id = payloadMap.get(idField).asText()
        logger.info("Got field for field $idField = $id")

        var sensorOverview = sensorOverviewRegion[id]

        if(sensorOverview == null)
        {
            logger.warn("Sensor NO found for $id")
            return
        }

        if(updateAlarm)
        {
            sensorOverview.alarmCount = sensorOverview.alarmCount + 1
        }
        else
        {
            sensorOverview.warningCount = sensorOverview.warningCount + 1
        }

        processPriority(sensorOverview)

        sensorOverviewRegion[id] = sensorOverview

    }

    private fun processPriority(sensorOverview: SensorOverview) {

        if(sensorOverview.alarmCount in (warningThreshold + 1)..alarmSevereThreshold)
        {
            sensorOverview.priority = warningPriority
        }
        else if(sensorOverview.alarmCount > alarmSevereThreshold)
            sensorOverview.priority = severePriority


        if(sensorOverview.warningCount > warningThreshold)
            sensorOverview.priority = warningPriority
    }
}