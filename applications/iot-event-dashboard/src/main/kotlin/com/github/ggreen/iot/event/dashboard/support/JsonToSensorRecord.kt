//package com.github.ggreen.iot.event.dashboard.support
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorRecord
//import io.r2dbc.postgresql.codec.Json
//import org.springframework.core.convert.converter.Converter
//
//
///**
// * @author Gregory Green
// */
//class JsonToSensorRecord(private val objectMapper: ObjectMapper) : Converter<Json,SensorRecord> {
//    override fun convert(json: Json): SensorRecord {
//            return objectMapper.readValue(json.asString(), SensorRecord::class.java)
//    }
//}