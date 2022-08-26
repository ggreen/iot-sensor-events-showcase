package com.github.ggreen.iot.event.dashboard.domains.sensor

import java.time.LocalDateTime

data class SensorReading(var id: String = "",
                         var value : Double = 0.0,
                         var priority : Short,
                         var captureTimestamp : LocalDateTime = LocalDateTime.now())
