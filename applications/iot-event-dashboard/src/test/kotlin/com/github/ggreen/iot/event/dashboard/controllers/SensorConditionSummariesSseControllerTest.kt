package com.github.ggreen.iot.event.dashboard.controllers

import com.github.ggreen.iot.event.dashboard.repositories.ConditionSummaryRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class SensorConditionSummariesSseControllerTest{
    private lateinit var subject: SensorConditionSummariesSseController
    private lateinit var repository: ConditionSummaryRepository

    @BeforeEach
    internal fun setUp() {
        repository = mock()

         subject = SensorConditionSummariesSseController(repository);
    }

    @Test
    internal fun summaries() {
        var actual = subject.summaries()?.blockFirst()
        verify(repository).findTotalConditionSummaries()
    }


    @Test
    internal fun groupBy() {
        var actual = subject.summariesGroupBy()?.blockFirst()
        verify(repository).findConditionSummariesGroupNyName()
    }
}