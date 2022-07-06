package com.github.ggreen.iot.event.dashboard.repository

import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorRecord
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
//ReactiveCrudRepository<SensorRecord,String>
interface SensorRecordRepository  {

     fun findAll(): Flux<SensorRecord>
}