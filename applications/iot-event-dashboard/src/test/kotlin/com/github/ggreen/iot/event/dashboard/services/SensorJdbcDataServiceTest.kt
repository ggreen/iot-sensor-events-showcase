package com.github.ggreen.iot.event.dashboard.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper

internal class SensorJdbcDataServiceTest{
    private lateinit var template: JdbcTemplate

    @BeforeEach
    internal fun setUp() {
        template = mock()
    }

    @Test
    internal fun findSensorOverviews() {

        var objectMapper = ObjectMapper()

        var expected : List<SensorOverview> = ArrayList(JavaBeanGeneratorCreator
            .of(SensorOverview::class.java)
            .createCollection(2))

        whenever(template.query(anyString(),any<RowMapper<SensorOverview>>()))
            .thenReturn(expected)


        var subject = SensorJdbcDataService(template)

        val actual = subject.findSensorOverviews()

        assertEquals(expected,actual);

    }
}