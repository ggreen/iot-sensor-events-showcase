package com.github.ggreen.iot.event.dashboard.controllers

import com.github.ggreen.iot.event.dashboard.domains.analytics.ConditionSummaries
import com.github.ggreen.iot.event.dashboard.repositories.ConditionSummaryRepository
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * @author Gregory Green
 */
@RestController
class SensorConditionSummariesSseController(private val repository: ConditionSummaryRepository) {

    @GetMapping("/summaries-sse")
    fun summaries(): Flux<ServerSentEvent<ConditionSummaries>>? {
        return Flux.interval(Duration.ofSeconds(1))
            .map<ServerSentEvent<ConditionSummaries>> { sequence: Long ->

                val builder = ServerSentEvent.builder<ConditionSummaries>()
                builder.id(sequence.toString())
                builder.data(repository.findTotalConditionSummaries())
                builder.build()
            }
    }

    @GetMapping("/summariesGroupBy-sse")
    fun summariesGroupBy(): Flux<ServerSentEvent<Iterable<ConditionSummaries>>>? {
        return Flux.interval(Duration.ofSeconds(2))
            .map<ServerSentEvent<Iterable<ConditionSummaries>>> { sequence: Long ->

                val builder = ServerSentEvent.builder<Iterable<ConditionSummaries>>()
                builder.id(sequence.toString())
                builder.data(repository.findConditionSummariesGroupNyName())
                builder.build()
            }
    }
}