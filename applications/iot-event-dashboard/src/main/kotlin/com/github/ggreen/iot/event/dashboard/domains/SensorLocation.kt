package com.github.ggreen.iot.event.dashboard.domains

data class SensorLocation(var locationName : String = "",
                          var latitude: Double =0.0,
                          var longitude: Double = 0.0)
