//package com.github.ggreen.iot.event.dashboard.repository
//
//import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
//import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorRecord
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.mockito.kotlin.any
//import org.mockito.kotlin.mock
//import org.mockito.kotlin.verify
//import org.mockito.kotlin.whenever
//import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
//import org.springframework.data.r2dbc.core.ReactiveSelectOperation
//import reactor.core.publisher.Flux
//
//
///**
// * @author Gregory Green
// * Test for SensorRecordDataRepository
// */
//internal class SensorRecordDataRepositoryTest {
//
//    private lateinit var all: Flux<SensorOverview>
//    private lateinit var reactiveSelect: ReactiveSelectOperation.ReactiveSelect<SensorOverview>
//    private lateinit var template: R2dbcEntityTemplate
//
//    @BeforeEach
//    internal fun setUp() {
//        template = mock()
//        reactiveSelect = mock()
//        all = mock()
//    }
//
//    @Test
//    internal fun findAll() {
//
//        whenever(template.select(any<Class<SensorOverview>>()))
//            .thenReturn(reactiveSelect)
//
//        whenever(reactiveSelect.all()).thenReturn(all)
//        val subject = SensorRecordDataRepository(template)
////        var actual : Flux<SensorRecord> = subject.findAll()
//
//        verify(template).select(any<Class<SensorOverview>>())
//    }
//}