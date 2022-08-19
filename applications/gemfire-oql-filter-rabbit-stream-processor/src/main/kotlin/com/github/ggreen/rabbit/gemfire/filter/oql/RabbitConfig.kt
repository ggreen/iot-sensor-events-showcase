package com.github.ggreen.rabbit.gemfire.filter.oql

import com.github.ggreen.rabbit.gemfire.filter.oql.service.OqlFilter
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
    private val applicationName: String ="gemfire-oql-filter-rabbit-stream-processor"

    @Value("\${rabbitmq.host}")
    private val host: String = ""

    @Value("\${rabbitmq.port}")
    private val port: Int = 5552

    @Value("\${rabbitmq.stream.in}")
    private val consumerStream: String = ""

    @Value("\${rabbitmq.stream.out}")
    private val publisherStream: String = ""


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
        env.streamCreator().stream(publisherStream).create()

        logger.info("RabbitMQ streams created consumerStream: $consumerStream publisherStream:$publisherStream")

        return env
    }

    @Bean
    fun consumer(environment: Environment, oqlFilter: OqlFilter) : Consumer {
        return environment.consumerBuilder().stream(consumerStream)
            .messageHandler(MessageHandler{
                    _, msg -> oqlFilter.apply(msg.bodyAsBinary)}
            ).build()
    }

    @Bean
    fun producer(environment : Environment) : Producer
    {
        return environment.producerBuilder().stream(publisherStream)
            .build()
    }
}