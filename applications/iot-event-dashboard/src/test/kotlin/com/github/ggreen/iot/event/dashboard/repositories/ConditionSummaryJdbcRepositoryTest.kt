package com.github.ggreen.iot.event.dashboard.repositories

import com.github.ggreen.iot.event.dashboard.domains.analytics.ConditionSummaries
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor

internal class ConditionSummaryJdbcRepositoryTest{
    private lateinit var template: JdbcTemplate

    @BeforeEach
    internal fun setUp() {
        template = mock()
    }

    @Test
    internal fun findSummaries() {
        val expected = JavaBeanGeneratorCreator.of(ConditionSummaries::class.java).create()
        var subject = ConditionSummaryJdbcRepository(template)
        whenever(template.query(any<String>(),any<ResultSetExtractor<ConditionSummaries>>()))
            .thenReturn(expected)
        var actual = subject.findTotalConditionSummaries()

        assertEquals(expected,actual);
    }
}