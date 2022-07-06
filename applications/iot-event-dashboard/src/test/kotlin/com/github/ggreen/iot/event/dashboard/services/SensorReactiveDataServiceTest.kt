//package com.github.ggreen.iot.event.dashboard.services
//
//import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
//import com.github.ggreen.iot.event.dashboard.repository.SensorRecordRepository
//import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.mockito.kotlin.mock
//import org.mockito.kotlin.verify
//import org.mockito.kotlin.whenever
//import reactor.core.publisher.Flux
//import java.util.function.Consumer
//
//internal class SensorReactiveDataServiceTest{
//    private lateinit var  repository: SensorRecordRepository
//
//    @BeforeEach
//    internal fun setUp() {
//        repository = mock()
//    }
//
//    @Test
//    internal fun findSensorOverviews() {
//
//        val expected = JavaBeanGeneratorCreator.of(SensorOverview::class.java)
//            .createCollection(1)
//
//        whenever(repository.findAll()).thenReturn(Flux.create(Consumer{ f ->  expected}))
//        var subject = SensorReactiveDataService(repository)
//
//        var actual = subject.findSensorRecords()
//
//        verify(repository).findAll()
//    }
//}