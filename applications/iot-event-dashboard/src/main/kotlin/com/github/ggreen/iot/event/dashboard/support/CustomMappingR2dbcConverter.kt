//package com.github.ggreen.iot.event.dashboard.support
//
//import io.r2dbc.spi.Row
//import org.springframework.data.mapping.context.MappingContext
//import org.springframework.data.r2dbc.convert.MappingR2dbcConverter
//import org.springframework.data.relational.core.mapping.RelationalPersistentEntity
//import org.springframework.data.relational.core.mapping.RelationalPersistentProperty
//
///**
// * @author Gregory Green
// */
//class CustomMappingR2dbcConverter(context: MappingContext<out RelationalPersistentEntity<*>, out RelationalPersistentProperty>) :
//    MappingR2dbcConverter(context) {
//    // ----------------------------------
//    // Entity reading
//    // ----------------------------------
//
////    override fun readValue(value: Any?, type: TypeInformation<*>): Any {
////
////        if(value == null)
////            return
////
////        println("Hello world")
////        return super.readValue(value, type)
////    }
////    override fun <R : Any?> read(type: Class<R>, row: Row): R {
////        println("Hello world")
////
////        return super.read(type, row)
////    }
//}