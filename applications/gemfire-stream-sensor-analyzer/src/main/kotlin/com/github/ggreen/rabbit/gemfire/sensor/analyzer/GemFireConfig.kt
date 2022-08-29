package com.github.ggreen.rabbit.gemfire.sensor.analyzer

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import com.vmware.data.services.gemfire.client.GemFireClient
import org.apache.geode.cache.Region
import org.apache.geode.pdx.PdxInstance
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Gregory Green
 */

@Configuration
class GemFireConfig {

    @Value("\${gemfire.region.name:sensorOverview}")
    private var regionName: String = "sensorOverview"

    @Value("\${spring.application.name}")
    private var applicationName: String = "gemfire-stream-sensor-analyzer"

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
    fun sensorOverviewRegion(geodeClient: GemFireClient) : Region<String, SensorOverview>
    {
        return geodeClient.getRegion<String,SensorOverview>(regionName)
    }

}