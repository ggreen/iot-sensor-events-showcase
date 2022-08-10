package com.github.ggreen.iot.event.dashboard.controllers

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import com.github.ggreen.iot.event.dashboard.repositories.SensorRepository
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * @author Gregory Green
 */
@RestController
class SensorOverviewSseController(private val sensorDataService: SensorRepository) {

    @GetMapping("/streamOverviewEvents-sse")
    fun streamOverviewEvents(): Flux<ServerSentEvent<Iterable<SensorOverview>>>? {
        return Flux.interval(Duration.ofSeconds(3))
            .map<ServerSentEvent<Iterable<SensorOverview>>> { sequence: Long ->

                var builder = ServerSentEvent.builder<Iterable<SensorOverview>>()
                builder.id(sequence.toString())

                builder.data(sensorDataService.findSensorOverviews())

                 builder.build()
            }
    }
}