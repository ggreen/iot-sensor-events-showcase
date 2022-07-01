package com.github.ggreen.iot.event.dashboard.domains

data class Sensor(var id: String = "",
                  var name: String = "",
                  var sensorLocation: SensorLocation? = null)
