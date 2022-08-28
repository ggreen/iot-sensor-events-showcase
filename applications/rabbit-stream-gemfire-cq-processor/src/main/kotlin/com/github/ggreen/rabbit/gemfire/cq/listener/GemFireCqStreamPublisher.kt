package com.github.ggreen.rabbit.gemfire.cq.listener

import com.github.ggreen.rabbit.gemfire.cq.publisher.StreamPublisher
import org.apache.geode.cache.query.CqEvent
import org.apache.geode.cache.util.CqListenerAdapter
import org.apache.geode.pdx.PdxInstance
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.function.Function


/**
 * GemFireCqStreamPublisher will publish a rabbit stream for each create/update record
 *
 * @author Gregory Green
 */
@Component
class GemFireCqStreamPublisher(
    private var publisher: StreamPublisher,
    @Qualifier("pdxToJsonConverter")
    private var pdxToJsonConverter: Function<PdxInstance, String>
) : CqListenerAdapter() {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun onEvent(cqEvent: CqEvent?) {

        log.info("cqEvent: $cqEvent")

        var baseOperation = cqEvent?.baseOperation

        if(!baseOperation!!.isUpdate && !baseOperation.isCreate)
            return //ignore non create/update

        var pdxInstance = cqEvent?.newValue as PdxInstance

        var json = pdxToJsonConverter.apply(pdxInstance)
        publisher.send(json)

    }



}