package com.github.ggreen.jdbc.batch.upsert.rabbit.stream

import com.github.ggreen.jdbc.batch.upsert.rabbit.stream.processor.JdbcBatchExecutor
import com.rabbitmq.stream.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Gregory Green
 */
@Configuration
class RabbitStreamSinkConfig {

    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${spring.application.name}")
    private val applicationName: String ="rabbit-stream-gemfire-cq-processor"

    @Value("\${rabbitmq.host}")
    private val host: String = ""

    @Value("\${rabbitmq.port}")
    private val port: Int = 5552

    @Value("\${rabbitmq.stream.in}")
    private val sinkStream: String = ""



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


        log.info("Creating sinkStream: $sinkStream")

        env.streamCreator().stream(sinkStream).create()

        return env

    }

    @Bean
    fun consumer(environment: Environment, jdbcBatchExecutor: JdbcBatchExecutor) : Consumer {
        return environment.consumerBuilder().stream(sinkStream)
            .offset(OffsetSpecification.first())
            .messageHandler(MessageHandler{
                    _, msg -> jdbcBatchExecutor.executeBatch(msg.bodyAsBinary)}
            ).build()
    }

}