package com.github.ggreen.rabbit.gemfire.filter.oql

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
    private val applicationName: String ="gemfire-stream-sink"

    @Value("\${rabbitmq.host}")
    private val host: String = ""

    @Value("\${rabbitmq.port}")
    private val port: Int = 5552

    @Value("\${rabbitmq.stream.in}")
    private val consumerStream: String = ""


    @Value("\${rabbitmq.username}")
    private val username: String = ""

    @Value("\${rabbitmq.password}")
    private val password: String = ""

    @Bean
    fun rabbitStreamEnv() : Environment
    {
        var env = Environment.builder()
            .host(host)
            .port(port)
            .username(username)
            .password(password)
            .id(applicationName)
            .build()

        env.streamCreator().stream(consumerStream).create()
//        env.streamCreator().stream(publisherStream).create()

        logger.info("RabbitMQ streams created consumerStream: $consumerStream")
        return env
    }

    @Bean
    fun consumer(environment: Environment, consumer: java.util.function.Consumer<ByteArray>) : Consumer {
        return environment.consumerBuilder().stream(consumerStream)
            .messageHandler(MessageHandler{
                    _, msg -> consumer.accept(msg.bodyAsBinary)}
            ).build()
    }

}