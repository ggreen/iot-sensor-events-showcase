package com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire

import com.github.ggreen.iot.event.dashboard.domains.analytics.ConditionSummaries
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import com.vmware.data.services.gemfire.io.QuerierService
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator
import org.apache.geode.cache.Region
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class ConditionSummaryGemFireRepositoryTest {

    private lateinit var sensorOverviewRegion: Region<String, SensorOverview>
    private lateinit var queryService: QuerierService
    private lateinit var summariesGroupNyNameRegion : Region<String, ConditionSummaries>

    private lateinit var subject: ConditionSummaryGemFireRepository

    @BeforeEach
    internal fun setUp() {
        queryService = mock()
        sensorOverviewRegion = mock()
        summariesGroupNyNameRegion = mock()
        subject = ConditionSummaryGemFireRepository(summariesGroupNyNameRegion,sensorOverviewRegion,queryService)
    }

    @Test
    fun findTotalConditionSummaries() {

        val expectedNormalCount = 3
        val expectedTotal = 10
        val expectedWarning = 3
        val expectedSevere = 4
        val keys :Set<String> = mock()

        whenever(this.sensorOverviewRegion.keySetOnServer()).thenReturn(keys)
        whenever(keys.count()).thenReturn(expectedTotal)

        whenever(queryService.query<Int>(any())).thenReturn(listOf(expectedWarning))
            .thenReturn(listOf(expectedSevere))

        val expected = ConditionSummaries(normalCount = expectedNormalCount,
            label = "total",
            totalCount = expectedTotal,
            warningCount = expectedWarning,
            severeCount = expectedSevere)


        val actual = subject.findTotalConditionSummaries()

        assertEquals(expected,actual);
    }

    @Test
    fun findConditionSummariesGroupNyName() {

        val expectedKey = "hello"
        val expected = JavaBeanGeneratorCreator.of(ConditionSummaries::class.java).create()
        val expectedList : Iterable<ConditionSummaries>? = arrayListOf(expected)
        val expectedMapOf = mapOf<String,ConditionSummaries>(expectedKey to expected)

        whenever(summariesGroupNyNameRegion.keySetOnServer()).thenReturn(setOf(expectedKey))
        whenever(summariesGroupNyNameRegion.getAll(any())).thenReturn(expectedMapOf)

        var actual = subject.findConditionSummariesGroupNyName()

        assertThat(actual).isNotEmpty
        assertThat(actual?.iterator()?.next()).isEqualTo(expectedList?.iterator()?.next())
    }
}