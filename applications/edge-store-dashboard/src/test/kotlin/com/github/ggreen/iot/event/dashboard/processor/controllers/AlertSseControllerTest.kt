package com.github.ggreen.iot.event.dashboard.processor.controllers

import com.github.ggreen.iot.event.dashboard.controllers.AlertSseController
import com.github.ggreen.iot.event.dashboard.processor.repositories.AlertRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class AlertSseControllerTest{

    private lateinit var repository: AlertRepository

    @BeforeEach
    internal fun setUp() {
        repository = mock()
    }

    @Test
    internal fun whenRequestForAlarmCount_Then_ReturnCount() {

        var subject = AlertSseController(repository)

        var actual = subject.alertCount()?.blockFirst()
        verify(repository).count()
    }
}