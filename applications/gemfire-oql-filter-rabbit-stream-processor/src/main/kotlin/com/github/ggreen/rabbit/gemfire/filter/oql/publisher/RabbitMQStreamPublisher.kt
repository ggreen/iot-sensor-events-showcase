package com.github.ggreen.rabbit.gemfire.filter.oql.publisher

import com.rabbitmq.stream.ConfirmationStatus
import com.rabbitmq.stream.Producer
import org.springframework.stereotype.Component

/**
 * @author Gregory Green
 */
@Component
class RabbitMQStreamPublisher(private val producer: Producer) : StreamPublisher {
    override fun send(json: ByteArray) {
        producer.send(
            producer.messageBuilder().addData(json).build()
        ) { confirmationStatus: ConfirmationStatus? -> }

    }
}