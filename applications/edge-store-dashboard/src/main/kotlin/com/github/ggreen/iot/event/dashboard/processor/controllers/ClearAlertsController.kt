package com.github.ggreen.iot.event.dashboard.processor.controllers

import com.github.ggreen.iot.event.dashboard.repositories.SensorRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * Web Controller for to clear alarm counts
 * @author Gregory Green
 */
@RestController
class ClearAlertsController(private val repository: SensorRepository) {

    @PostMapping("clearAlerts")
    fun clearAlerts(): Int {
        return repository.updateAllSensorOverviewAlarmCounts(0,0)
    }
}