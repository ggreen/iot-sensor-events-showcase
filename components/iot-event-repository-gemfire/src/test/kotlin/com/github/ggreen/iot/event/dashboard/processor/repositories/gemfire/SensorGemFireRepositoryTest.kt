package com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator
import org.apache.geode.cache.Region
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class SensorGemFireRepositoryTest {


    private lateinit var region : Region<String, SensorOverview>
    private lateinit var subject: SensorGemFireRepository

    @BeforeEach
    internal fun setUp() {
        region = mock()
        subject = SensorGemFireRepository(region)
    }

    @Test
    fun findSensorOverviews() {

        var expectedSensorOverview : SensorOverview = JavaBeanGeneratorCreator.of(SensorOverview::class.java).create()

        val expectedIterable = arrayListOf<SensorOverview>(
            expectedSensorOverview
        )

        val expectedKey = ""
        val expectedMap = mapOf<String,SensorOverview>(expectedKey to expectedSensorOverview)

        whenever(region.getAll(any())).thenReturn(expectedMap)
        println("expect: $expectedIterable")

        var actual = subject.findSensorOverviews()

        assertThat(actual).isNotEmpty
        assertEquals(expectedIterable[0],actual.iterator().next());
    }

    @Test
    fun updateAllSensorOverviewAlarmCounts() {
        val expectedUpdateCount = 2
        val expectedAlarmCount = 10
        val expectedPriority : Short = 10


        val expectSensorOverview = JavaBeanGeneratorCreator.of(SensorOverview::class.java).create()
        expectSensorOverview.alarmCount = 3
        expectSensorOverview.priority = 3

        val expectedKeys = setOf<String>("A","B")
        whenever(region.keySetOnServer()).thenReturn(expectedKeys)
        whenever(region[any()]).thenReturn(expectSensorOverview)

        var actual = subject.updateAllSensorOverviewAlarmCounts(expectedAlarmCount,expectedPriority)

        assertEquals(expectedUpdateCount,actual);
        assertEquals(expectedAlarmCount,expectSensorOverview.alarmCount.toInt())
        assertEquals(expectedAlarmCount,expectSensorOverview.warningCount.toInt())
        assertEquals(expectedPriority,expectSensorOverview.priority)

        verify(region, atLeastOnce())[any()] = any()

    }
}