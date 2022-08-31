package com.github.ggreen.iot.event.dashboard.processor

import com.github.ggreen.iot.event.dashboard.domains.analytics.ConditionSummaries
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire.AlertGemFireRepository
import com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire.ConditionSummaryGemFireRepository
import com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire.SensorGemFireRepository
import com.vmware.data.services.gemfire.client.GemFireClient
import com.vmware.data.services.gemfire.io.QuerierService
import org.apache.geode.cache.Region
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * @author Gregory Green
 */
@Configuration
@ComponentScan(basePackageClasses = [AlertGemFireRepository::class,SensorGemFireRepository::class,ConditionSummaryGemFireRepository::class])
class GemFireConfig {

    private val logger = LoggerFactory.getLogger("GemFireConfig")

    @Value("\${spring.application.name}")
    private lateinit var applicationName: String

    @Value("\${gemfire.locators}")
    private lateinit var locators: String

    @Bean
    fun gemFireClient() : GemFireClient
    {
        logger.info("Creating connection for locators $locators")
        return GemFireClient
            .builder()
            .locators(locators)
            .clientName(applicationName)
            .build();
    }

    @Bean
    fun queryService( gemFireClient: GemFireClient) : QuerierService {
        return gemFireClient.querierService
    }

    @Bean
    fun querierService(gemFireClient: GemFireClient) : QuerierService
    {
        return gemFireClient.querierService
    }

    @Bean
    fun summariesGroupNyName(gemFireClient: GemFireClient) : Region<String, ConditionSummaries>
    {
        return gemFireClient.getRegion<String, ConditionSummaries>("summariesGroupNyName")
    }

    @Bean("summariesTotal")
    fun summariesTotal(gemFireClient: GemFireClient): Region<String, ConditionSummaries> {
        return gemFireClient.getRegion("summariesTotal")
    }

    @Bean("sensorOverview")
    fun sensorOverview(gemFireClient: GemFireClient) : Region<String, SensorOverview>
    {
        return gemFireClient.getRegion<String, SensorOverview>("sensorOverview")
    }

}