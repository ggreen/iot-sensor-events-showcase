package com.github.ggreen.iot.event.dashboard.controllers

import com.github.ggreen.iot.event.dashboard.processor.repositories.AlertRepository
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * @author Gregory Green
 */
@RestController
class AlertSseController(private val alertRepository: AlertRepository) {

    @GetMapping("/alertCountEvents-sse")
    fun alertCount(): Flux<ServerSentEvent<Long>>? {
        return Flux.interval(Duration.ofSeconds(1))
            .map<ServerSentEvent<Long>> { sequence: Long ->

                var builder = ServerSentEvent.builder<Long>()
                builder.id(sequence.toString())

               builder.data(alertRepository.count())

                 builder.build()
            }
    }
}