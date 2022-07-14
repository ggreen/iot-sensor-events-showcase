package com.github.ggreen.rabbit.gemfire.cq.publisher

import com.rabbitmq.stream.ConfirmationStatus
import com.rabbitmq.stream.Producer
import org.springframework.stereotype.Component

/**
 * @author Gregory Green
 */
@Component
class RabbitMQStreamPublisher(private val producer: Producer) : StreamPublisher {
    override fun send(json: String) {
        producer.send(
            producer.messageBuilder().addData(json.toByteArray(Charsets.UTF_8)).build()
        ) { confirmationStatus: ConfirmationStatus? -> }

    }
}