package com.github.ggreen.iot.event.dashboard.processor

import com.github.ggreen.iot.event.dashboard.domains.analytics.ConditionSummaries
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire.AlertGemFireRepository
import com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire.ConditionSummaryGemFireRepository
import com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire.SensorGemFireRepository
import com.github.ggreen.iot.event.dashboard.repositories.ConditionSummaryRepository
import com.vmware.data.services.gemfire.client.GemFireClient
import com.vmware.data.services.gemfire.io.QuerierService
import org.apache.geode.cache.Region
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

    @Value("\${spring.application.name}")
    private var applicationName: String = "gemfire-stream-sink"

    @Value("\${gemfire.locators:gemfire.locator}")
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
    fun summariesTotal(gemFireClient: GemFireClient) : Region<String, ConditionSummaries>
    {
        var region = gemFireClient.getRegion<String, ConditionSummaries>("summariesTotal")
        return region
    }

    @Bean("sensorOverview")
    fun sensorOverview(gemFireClient: GemFireClient) : Region<String, SensorOverview>
    {
        return gemFireClient.getRegion<String, SensorOverview>("sensorOverview")
    }

//    @Bean
//    fun sensorGemFireRepository(sensorOverviewRegion: Region<String, SensorOverview>) : SensorGemFireRepository
//    {
//        return SensorGemFireRepository(sensorOverviewRegion)
//    }

//    @Bean
//    fun alertRepository(queryService: QuerierService) : AlertGemFireRepository
//    {
//        return AlertGemFireRepository(queryService)
//    }

//    fun conditionSummaryRepository() : ConditionSummaryRepository
//    {
//        return ConditionSummaryGemFireRepository()
//    }
}