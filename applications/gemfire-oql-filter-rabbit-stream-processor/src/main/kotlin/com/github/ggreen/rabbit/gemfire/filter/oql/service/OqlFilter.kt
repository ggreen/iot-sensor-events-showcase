package com.github.ggreen.rabbit.gemfire.filter.oql.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ggreen.rabbit.gemfire.filter.oql.publisher.StreamPublisher
import com.vmware.data.services.gemfire.io.QuerierService
import nyla.solutions.core.patterns.decorator.TextStylist
import nyla.solutions.core.util.Text
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.function.Function

/**
 * @author Gregory Green
 */
@Component
class OqlFilter(private val queryService: QuerierService,
                @Value("\${filter.query}")
                private var oql: String,
                private val publisher: StreamPublisher,
                private val textStylist : TextStylist
) : Function<ByteArray,ByteArray?> {
    private val logger: Logger = LoggerFactory.getLogger(OqlFilter::class.java)
    private val objectMapper = ObjectMapper()


    init {
        logger.info("Configured OQL:$oql")
        oql = Text.trim(oql,'\'')
        logger.info("Cleaned OQL:$oql")
    }


    /**
     * Applies this function to the given argument.
     *
     * @param payload the function argument
     * @return the function result
     */
    override fun apply(payload: ByteArray): ByteArray? {
        val jsonMap = objectMapper.readValue(payload,Map::class.java)
        logger.info("OQL: $oql \nMAP: $jsonMap")

        val formattedOql = textStylist.format(oql,jsonMap)
        logger.info(formattedOql)

        val result = queryService.query<Any?>(formattedOql)

        if(result != null && !result.isEmpty()) {

            logger.info("Sending payload")

            publisher.send(payload)
            return payload
        }

        return null
    }
}