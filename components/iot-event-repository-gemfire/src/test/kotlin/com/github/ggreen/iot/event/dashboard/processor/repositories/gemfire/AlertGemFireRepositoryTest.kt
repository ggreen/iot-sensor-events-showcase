package com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire

import com.vmware.data.services.gemfire.io.QuerierService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class AlertGemFireRepositoryTest {

    private lateinit var querierService: QuerierService
    private lateinit var subject: AlertGemFireRepository


    @BeforeEach
    internal fun setUp() {
        querierService = mock()
        subject = AlertGemFireRepository(querierService)
    }

    @Test
    fun count() {
        val expected = 3L
        var arrayExpected = mutableListOf<Long>(expected)
        whenever(querierService.query<Long>(any<String>())).thenReturn(arrayExpected)

        val actual = subject.count()
        assertEquals(expected,actual);
    }
}