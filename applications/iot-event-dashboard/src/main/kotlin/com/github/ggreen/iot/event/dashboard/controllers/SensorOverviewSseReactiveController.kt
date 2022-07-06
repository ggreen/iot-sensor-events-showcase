//package com.github.ggreen.iot.event.dashboard.controllers
//
//import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorRecord
//import com.github.ggreen.iot.event.dashboard.services.SensorReactiveDataService
//import org.springframework.web.bind.annotation.RestController
//import reactor.core.publisher.Flux
//
///**
// * @author Gregory Green
// */
//@RestController
//class SensorOverviewSseReactiveController(private val sensorDataService: SensorReactiveDataService) {
//
////    @GetMapping("/streamOverviewEvents-sse")
//    fun streamOverviewEvents(): Flux<SensorRecord> {
//        return sensorDataService.findSensorRecords()
////    }
//
//}