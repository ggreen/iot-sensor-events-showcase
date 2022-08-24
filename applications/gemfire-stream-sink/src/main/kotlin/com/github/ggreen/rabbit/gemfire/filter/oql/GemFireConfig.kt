package com.github.ggreen.rabbit.gemfire.filter.oql

import com.github.ggreen.rabbit.gemfire.processor.GemFireSaveConsumer
import com.vmware.data.services.gemfire.client.GemFireClient
import com.vmware.data.services.gemfire.io.QuerierService
import org.apache.geode.cache.Region
import org.apache.geode.pdx.JSONFormatter
import org.apache.geode.pdx.PdxInstance
import java.util.function.Function
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * @author Gregory Green
 */

@Configuration
@ComponentScan(basePackageClasses = [GemFireSaveConsumer::class])
class GemFireConfig {

    @Value("\${gemfire.region.name}")
    private lateinit var regionName: String

    @Value("\${spring.application.name}")
    private var applicationName: String = "gemfire-stream-sink"

    @Value("\${gemfire.locators}")
    private var locators: String = "localhost[10334]"

    @Bean
    fun gemFireClient() : GemFireClient
    {
        return GemFireClient
            .builder()
            .locators(locators)
            .clientName(applicationName)
            .build();
    }

    @Bean
    fun queryService( gemFireClient: GemFireClient) : QuerierService{
        return gemFireClient.querierService
    }

    @Bean
    fun region(geodeClient: GemFireClient) : Region<String, PdxInstance>
    {
        return geodeClient.getRegion<String,PdxInstance>(regionName)
    }


    @Bean
    fun bytesToPdxConverter(gemFireClient: GemFireClient)  : java.util.function.Function<ByteArray, PdxInstance>
    {
        return  Function<ByteArray, PdxInstance>
        { payload : ByteArray ->  JSONFormatter.fromJSON(payload)
        }
    }

    @Bean("jsonBytesToPdxConverter")
    fun binaryToPdxConverter(geodeClient: GemFireClient): java.util.function.Function<ByteArray, PdxInstance> {
        return Function<ByteArray, PdxInstance>{ jsonBytes ->
            JSONFormatter.fromJSON(jsonBytes)
        }
    }

    @Bean("pdxToJsonConverter")
    fun pdxToJsonConverter(): java.util.function.Function<PdxInstance, String> {
        return Function<PdxInstance, String>{ pdx : PdxInstance ->  JSONFormatter.toJSON(pdx) }
    }

}