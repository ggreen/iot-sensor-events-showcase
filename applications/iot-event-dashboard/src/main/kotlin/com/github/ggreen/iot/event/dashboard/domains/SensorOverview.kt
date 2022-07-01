package com.github.ggreen.iot.event.dashboard.domains

data class SensorOverview(var id : String = "",
                          var status : Short = 0,
                          var priority : Short = 0,
                          var sensor: Sensor? = null)
