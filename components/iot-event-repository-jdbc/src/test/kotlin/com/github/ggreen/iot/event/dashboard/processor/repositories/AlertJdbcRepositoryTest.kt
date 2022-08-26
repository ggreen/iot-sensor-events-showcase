package com.github.ggreen.iot.event.dashboard.processor.repositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.jdbc.core.JdbcTemplate

internal class AlertJdbcRepositoryTest{
    private lateinit var template: JdbcTemplate

    @BeforeEach
    internal fun setUp() {
        template = mock()
    }

    @Test
    internal fun given_template_whenCount_Then_ReturnCount() {
        val expected = 10L

        whenever(template.queryForObject(anyString(),any<Class<Long>>())).thenReturn(expected)
        var subject = AlertJdbcRepository(template)

        var actual = subject.count()
        assertEquals(expected,actual);
    }
}