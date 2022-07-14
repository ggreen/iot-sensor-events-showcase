package com.github.ggreen.rabbit.gemfire.cq.listener

import com.github.ggreen.rabbit.gemfire.cq.publisher.StreamPublisher
import org.apache.geode.cache.Operation
import org.apache.geode.cache.query.CqEvent
import org.apache.geode.pdx.PdxInstance
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.function.Function

internal class GemFireCqStreamPublisherTest {

    private lateinit var newValue: PdxInstance
    private lateinit var baseOperation: Operation
    private lateinit var cqEvent: CqEvent
    private lateinit var publisher: StreamPublisher
    private lateinit var pdxToJsonConverter : Function<PdxInstance,String>


    @BeforeEach
    internal fun setUp() {
        cqEvent = mock()
        publisher = mock()
        pdxToJsonConverter = mock()
        baseOperation = mock()
        newValue = mock()
    }

    @Test
    fun given_createEvent_whenOnEvent_ThenSendMessage() {

        val expectedJson = "{}"

        buildSetup(expectedJson,expectedCreate = true, expectedUpdate = false)

        var subject = GemFireCqStreamPublisher(publisher,pdxToJsonConverter)

        subject.onEvent(cqEvent)

        verify(publisher).send(anyString())

    }

    @Test
    fun given_updateEvent_whenOnEvent_ThenSendMessage() {

        val expectedJson = "{}"

        buildSetup(expectedJson,expectedCreate = false, expectedUpdate = true)

        var subject = GemFireCqStreamPublisher(publisher,pdxToJsonConverter)

        subject.onEvent(cqEvent)

        verify(publisher).send(anyString())

    }

    private fun buildSetup(expectedJson: String, expectedCreate : Boolean, expectedUpdate : Boolean) {
        whenever(cqEvent.baseOperation).thenReturn(baseOperation)
        whenever(baseOperation.isUpdate).thenReturn(expectedUpdate)
        whenever(baseOperation.isCreate).thenReturn(expectedCreate)
        whenever(cqEvent.newValue).thenReturn(newValue)
        whenever(pdxToJsonConverter.apply(any())).thenReturn(expectedJson)
    }
}