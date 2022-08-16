package com.github.ggreen.rabbit.gemfire.filter.oql

import com.vmware.data.services.gemfire.client.GemFireClient
import com.vmware.data.services.gemfire.io.QuerierService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Gregory Green
 */

@Configuration
class GemFireConfig {

    @Bean
    fun gemFireClient() : GemFireClient
    {
        return GemFireClient.connect()
    }

    @Bean
    fun queryService( gemFireClient: GemFireClient) : QuerierService{
        return gemFireClient.querierService
    }

}