package com.github.ggreen.rabbit.gemfire.cq

import com.github.ggreen.rabbit.gemfire.cq.processor.GemFireSaveConsumer
import com.rabbitmq.stream.Consumer
import com.rabbitmq.stream.Environment
import com.rabbitmq.stream.MessageHandler
import com.rabbitmq.stream.Producer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Gregory Green
 */
@Configuration
class RabbitConfig {
    @Value("\${spring.application.name}")
    private val applicationName: String ="rabbit-stream-gemfire-cq-processor"

    @Value("\${rabbitmq.host}")
    private val host: String = ""

    @Value("\${rabbitmq.port}")
    private val port: Int = 5552

    @Value("\${rabbitmq.stream.in}")
    private val consumerStream: String = ""

    @Value("\${rabbitmq.stream.out}")
    private val alertStream: String = ""


    @Value("\${rabbitmq.username}")
    private val username: String = ""

    @Value("\${rabbitmq.password}")
    private val password: String = ""

    private val maxTrackingConsumersByConnection: Int = 1
    private val maxConsumersByConnection: Int = 1

    @Bean
    fun rabbitStreamEnv() : Environment
    {
        var env = Environment.builder()
            .maxConsumersByConnection(maxConsumersByConnection)
            .maxTrackingConsumersByConnection(maxTrackingConsumersByConnection)
            .host(host)
            .port(port)
            .username(username)
            .password(password)
            .id(applicationName)
            .build()


        env.streamCreator().stream(consumerStream).create()
        env.streamCreator().stream(alertStream).create()


        return env

    }

    @Bean
    fun consumer(environment: Environment, gemFireSaveConsumer: GemFireSaveConsumer) : Consumer {
        return environment.consumerBuilder().stream(consumerStream)
            .messageHandler(MessageHandler{
                    context, msg -> gemFireSaveConsumer.accept(msg.bodyAsBinary)}
            ).build()
    }


    @Bean
    fun producer(environment : Environment) : Producer
    {
        return environment.producerBuilder().stream(alertStream)
//            .routing(Function { msg: Message ->
//                msg.properties.messageIdAsString
//            })
//            .producerBuilder()
            .build()
    }
}