package com.github.ggreen.rabbit.gemfire.cq

import com.github.ggreen.rabbit.gemfire.cq.listener.GemFireCqStreamPublisher
import com.vmware.data.services.gemfire.client.GemFireClient
import org.apache.geode.pdx.JSONFormatter
import org.apache.geode.cache.Region
import org.apache.geode.cache.query.CqAttributesFactory
import org.apache.geode.cache.query.CqQuery
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

    @Value("\${gemfire.cq.cqName:rabbit-stream-gemfire-cq-processor}")
    private lateinit var cqName: String


    private val log = LoggerFactory.getLogger(javaClass)


    @Bean
    fun geodeClient() : GemFireClient
    {
        return GemFireClient.connect()
    }

    @Bean
    fun cqQuery(geodeClient: GemFireClient,gemFireCqStreamPublisher : GemFireCqStreamPublisher): CqQuery? {

        var clientCache = geodeClient.clientCache
        var queryService = clientCache.queryService;
        var cqf = CqAttributesFactory()

        cqf.addCqListener(gemFireCqStreamPublisher)
        val cqa = cqf.create()

        log.info("cqName: $cqName OQL: $oql")

        var cqQuery = queryService.newCq(cqName, oql, cqa);
        cqQuery.execute()

        return cqQuery
    }

    @Bean
    fun bytesToPdxConverter(gemFireClient: GemFireClient)  : Function<ByteArray, PdxInstance>
    {
        return  Function<ByteArray, PdxInstance>
        { payload : ByteArray ->  JSONFormatter.fromJSON(payload)
        }
    }

    @Bean("jsonBytesToPdxConverter")
    fun binaryToPdxConverter(geodeClient: GemFireClient): Function<ByteArray, PdxInstance>{
        return Function<ByteArray, PdxInstance>{ jsonBytes ->
            JSONFormatter.fromJSON(jsonBytes)
        }
    }

    @Bean("pdxToJsonConverter")
    fun f(): Function<PdxInstance, String>{
        return Function<PdxInstance, String>{ pdx : PdxInstance ->  JSONFormatter.toJSON(pdx) }
    }

    @Bean
    fun region(geodeClient: GemFireClient) : Region<String, PdxInstance>
    {
        return geodeClient.getRegion<String,PdxInstance>(regionName)
    }

}