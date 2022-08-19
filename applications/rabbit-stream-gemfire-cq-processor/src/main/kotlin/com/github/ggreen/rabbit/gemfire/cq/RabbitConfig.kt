package com.github.ggreen.rabbit.gemfire.cq

import com.github.ggreen.rabbit.gemfire.cq.processor.GemFireSaveConsumer
import com.rabbitmq.stream.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Gregory Green
 */
@Configuration
class RabbitConfig {
    private val logger: Logger = LoggerFactory.getLogger(RabbitConfig::class.java)

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

        logger.info("RabbitMQ streams created consumerStream: $consumerStream alertStream:$alertStream")

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