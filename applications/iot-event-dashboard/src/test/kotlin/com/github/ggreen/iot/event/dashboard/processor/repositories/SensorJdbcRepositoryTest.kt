package com.github.ggreen.iot.event.dashboard.repositories

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper

/**
 * Test class for SensorJdbcRepository
 * @author Gregory Green
 */
internal class SensorJdbcRepositoryTest{
    private lateinit var template: JdbcTemplate
    private lateinit var subject: SensorJdbcRepository

    @BeforeEach
    internal fun setUp() {
        template = mock()

        subject = SensorJdbcRepository(template)
    }

    @Test
    internal fun findSensorOverviews() {

        var expected : List<SensorOverview> = ArrayList(JavaBeanGeneratorCreator
            .of(SensorOverview::class.java)
            .createCollection(2))

        whenever(template.query(anyString(),any<RowMapper<SensorOverview>>()))
            .thenReturn(expected)


        val actual = subject.findSensorOverviews()

        assertEquals(expected,actual);

    }

    @DisplayName("GIVEN alert count WHEN update THEN return expected number of rows")
    @Test
    internal fun givenAlertCount_WhenUpdate_Then_ReturnExpectedNumberOfRows() {
        val expectedAlarmCount :Int = 3
        val expectedPriority : Short = 1

        whenever(template.update(anyString(), any<Int>())).thenReturn(expectedAlarmCount)
        var actual = subject.updateAllSensorOverviewAlarmCounts(expectedAlarmCount,expectedPriority)

        assertEquals(expectedAlarmCount,actual);
    }
}