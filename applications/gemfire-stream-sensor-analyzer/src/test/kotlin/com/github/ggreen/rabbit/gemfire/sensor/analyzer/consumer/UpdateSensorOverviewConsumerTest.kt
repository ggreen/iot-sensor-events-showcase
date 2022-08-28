package com.github.ggreen.rabbit.gemfire.sensor.analyzer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import org.apache.geode.cache.Region
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class UpdateSensorOverviewConsumerTest{
    private val objectMapper = ObjectMapper()
    private lateinit var sensorOverviewRegion: Region<String, SensorOverview>

    @BeforeEach
    internal fun setUp() {
        sensorOverviewRegion = mock()
    }

    @Test
    internal fun given_alarmOnStream_when_accept_then_SensorAlertCount_Increased() {

        var sensorOverview = SensorOverview()
        sensorOverview.priority = 0
        sensorOverview.alarmCount = 0
        sensorOverview.warningCount = 0


        var expected = SensorOverview()
        expected.priority = 0
        expected.alarmCount = 1
        expected.warningCount = 0

        whenever(sensorOverviewRegion[any()]).thenReturn(sensorOverview)

        val payloadBytes = objectMapper.writeValueAsBytes(sensorOverview)

        val updateAlarm = true
        var subject = UpdateSensorOverviewConsumer(sensorOverviewRegion, updateAlarm,"id")

        subject.accept(payloadBytes)

        assertEquals(expected,sensorOverview);

    }


    @Test
    internal fun given_warnOnStream_when_accept_then_SensorAlertCount_Increased() {

        var sensorOverview = SensorOverview()
        sensorOverview.priority = 0
        sensorOverview.alarmCount = 0
        sensorOverview.warningCount = 0


        val payloadBytes = objectMapper.writeValueAsBytes(sensorOverview)


        var expected = SensorOverview()
        expected.priority = 0
        expected.alarmCount = 0
        expected.warningCount = 1

        whenever(sensorOverviewRegion[any()]).thenReturn(sensorOverview)


        val updateAlarm = false
        var subject = UpdateSensorOverviewConsumer(sensorOverviewRegion, updateAlarm,"id")

        subject.accept(payloadBytes)

        assertEquals(expected,sensorOverview);

    }


        @Test
        internal fun given_alarmCount_over_severeThreshold_When_accept_Then_prioritySetToTwo() {

            var expected = SensorOverview()
            expected.priority = 2
            expected.alarmCount = 11
            expected.warningCount = 0


            var sensorOverview = SensorOverview()
            sensorOverview.priority = 0
            sensorOverview.alarmCount = 10
            sensorOverview.warningCount = 0
            whenever(sensorOverviewRegion[any()]).thenReturn(sensorOverview)


            val payloadBytes = objectMapper.writeValueAsBytes(sensorOverview)

            val updateAlarm = true
            var subject = UpdateSensorOverviewConsumer(sensorOverviewRegion, updateAlarm,"id")

            subject.accept(payloadBytes)

            assertEquals(expected,sensorOverview);
        }



        @Test
        internal fun given_alarmCount_between_warnAndBelowSevereThreshold_When_accept_Then_prioritySetTo1() {

            var expected = SensorOverview()
            expected.priority = 1
            expected.alarmCount = 5
            expected.warningCount = 0


            var sensorOverview = SensorOverview()
            sensorOverview.priority = 0
            sensorOverview.alarmCount = 4
            sensorOverview.warningCount = 0
            whenever(sensorOverviewRegion[any()]).thenReturn(sensorOverview)


            val payloadBytes = objectMapper.writeValueAsBytes(sensorOverview)

            val updateAlarm = true
            var subject = UpdateSensorOverviewConsumer(sensorOverviewRegion, updateAlarm,"id")

            subject.accept(payloadBytes)

            assertEquals(expected,sensorOverview);
        }
        @Test
        internal fun given_alarmCount_lessThenWarnThreshold_When_accept_Then_prioritySetTo0() {

            var expected = SensorOverview()
            expected.priority = 0
            expected.alarmCount = 4
            expected.warningCount = 0


            var sensorOverview = SensorOverview()
            sensorOverview.priority = 0
            sensorOverview.alarmCount = 3
            sensorOverview.warningCount = 0
            whenever(sensorOverviewRegion[any()]).thenReturn(sensorOverview)


            val payloadBytes = objectMapper.writeValueAsBytes(sensorOverview)

            val updateAlarm = true
            var subject = UpdateSensorOverviewConsumer(sensorOverviewRegion, updateAlarm,"id")

            subject.accept(payloadBytes)

            assertEquals(expected,sensorOverview);
        }


    @Test
    internal fun given_warningCountOverThreshold_when_accept_Then_priorty_setTo1() {
        var expected = SensorOverview()
        expected.priority = 1
        expected.alarmCount = 0
        expected.warningCount = 5


        var sensorOverview = SensorOverview()
        sensorOverview.priority = 0
        sensorOverview.alarmCount = 0
        sensorOverview.warningCount = 4
        whenever(sensorOverviewRegion[any()]).thenReturn(sensorOverview)


        val payloadBytes = objectMapper.writeValueAsBytes(sensorOverview)

        val updateAlarm = false
        var subject = UpdateSensorOverviewConsumer(sensorOverviewRegion, updateAlarm,"id")

        subject.accept(payloadBytes)

        assertEquals(expected,sensorOverview);
    }
}