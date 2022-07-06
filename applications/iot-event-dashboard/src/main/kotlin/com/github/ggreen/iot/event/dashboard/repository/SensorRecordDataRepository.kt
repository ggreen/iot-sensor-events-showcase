//package com.github.ggreen.iot.event.dashboard.repository
//
//import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorRecord
//import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
//import reactor.core.publisher.Flux
//
///**
// * @author Gregory Green
// */
////@Repository
//class SensorRecordDataRepository(private val template: R2dbcEntityTemplate) : SensorRecordRepository {
//    override fun findAll(): Flux<SensorRecord> {
//        return template.select(SensorRecord::class.java).all()
//    }
//}