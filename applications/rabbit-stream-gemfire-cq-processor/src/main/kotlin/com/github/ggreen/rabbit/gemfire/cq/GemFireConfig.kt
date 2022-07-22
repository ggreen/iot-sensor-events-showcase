package com.github.ggreen.rabbit.gemfire.cq

import com.github.ggreen.rabbit.gemfire.cq.listener.GemFireCqStreamPublisher
import io.pivotal.services.dataTx.geode.client.GeodeClient
import org.apache.geode.cache.Region
import org.apache.geode.cache.query.CqAttributesFactory
import org.apache.geode.cache.query.CqQuery
import org.apache.geode.pdx.JSONFormatter
import org.apache.geode.pdx.PdxInstance
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Function

/**
 * @author Gregory Green
 */

@Configuration
class GemFireConfig {

    @Value("\${gemfire.region.name}")
    private lateinit var regionName: String

    @Value("\${gemfire.cq.oql}")
    private lateinit var oql: String

    @Value("\${spring.application.name}")
    private lateinit var cqName: String

    @Bean
    fun bytesToPdxConverter(geodeClient: GeodeClient)  : Function<ByteArray, PdxInstance>
    {
        return  Function<ByteArray, PdxInstance>
        { payload : ByteArray ->  JSONFormatter.fromJSON(payload)
        }
    }

    @Bean
    fun geodeClient() : GeodeClient
    {
        return GeodeClient.connect()
    }

    @Bean
    fun cqQuery(geodeClient: GeodeClient,gemFireCqStreamPublisher : GemFireCqStreamPublisher): CqQuery? {

        var clientCache = geodeClient.clientCache
        var queryService = clientCache.queryService;
        var cqf = CqAttributesFactory()

        cqf.addCqListener(gemFireCqStreamPublisher)
        val cqa = cqf.create()

        var log = LoggerFactory.getLogger(javaClass)
        log.info("OQL: $oql")

        var cqQuery = queryService.newCq(cqName, oql, cqa);
        cqQuery.execute()

        return cqQuery
    }

    @Bean("jsonBytesToPdxConverter")
    fun binaryToPdxConverter(geodeClient: GeodeClient): Function<ByteArray, PdxInstance>{
        return Function<ByteArray, PdxInstance>{ jsonBytes ->
            JSONFormatter.fromJSON(jsonBytes)
        }
    }

    @Bean("pdxToJsonConverter")
    fun f(): Function<PdxInstance, String>{
        return Function<PdxInstance, String>{ pdx : PdxInstance ->  JSONFormatter.toJSON(pdx) }
    }

    @Bean
    fun region(geodeClient: GeodeClient) : Region<String, PdxInstance>
    {
        return geodeClient.getRegion<String,PdxInstance>(regionName)
    }

}