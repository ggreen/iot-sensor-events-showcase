//package com.github.ggreen.iot.event.dashboard.services
//
//import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorRecord
//import com.github.ggreen.iot.event.dashboard.repository.SensorRecordRepository
//import org.springframework.stereotype.Service
//import reactor.core.publisher.Flux
//
///**
// * @author Gregory Green
// */
//@Service
//class SensorReactiveDataService(private val repository: SensorRecordRepository)  {
//    fun findSensorRecords(): Flux<SensorRecord> {
//        return repository.findAll()
//    }
//}