package com.github.ggreen.iot.event.dashboard.domains.sensor

data class Sensor(var id: String = "",
                  var name: String = "",
                  var sensorLocation: SensorLocation? = null)
