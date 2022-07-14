package com.github.ggreen.rabbit.gemfire.cq.publisher

import com.rabbitmq.stream.Message
import com.rabbitmq.stream.MessageBuilder
import com.rabbitmq.stream.Producer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class RabbitMQStreamPublisherTest{
    private lateinit var message: Message
    private lateinit var messageBuilder: MessageBuilder
    private lateinit var producer: Producer

    @BeforeEach
    internal fun setUp() {
        producer = mock()
        messageBuilder = mock()
        message = mock()
    }

    @Test
    internal fun send() {

        whenever(producer.messageBuilder()).thenReturn(messageBuilder)
        whenever(messageBuilder.addData(any())).thenReturn(messageBuilder)
        whenever(messageBuilder.build()).thenReturn(message)

        var subject = RabbitMQStreamPublisher(producer)

        val json = "{}"
        subject.send(json)

        verify(producer).send(any(),any())
    }
}