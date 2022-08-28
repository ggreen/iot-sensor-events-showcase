package com.github.ggreen.iot.event.dashboard.processor.load

import com.github.ggreen.iot.event.dashboard.domains.sensor.Sensor
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorLocation
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import org.apache.geode.cache.Region
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

/**
 * @author Gregory Green
 */
@Component
class GemFireDataLoader(private val sensorOverviewRegion: Region<String, SensorOverview>) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        var sensorOverview = SensorOverview()
        sensorOverview.id = "1"
        sensorOverview.sensor = Sensor()
        sensorOverview.sensor!!.name = "Milk Temperature"
        sensorOverview.sensor!!.sensorLocation = SensorLocation()
        sensorOverview.sensor!!.sensorLocation!!.locationName = "Aisle ${sensorOverview.id}"
        save(sensorOverview)

        sensorOverview.id = "2"
        sensorOverview.sensor = Sensor()
        sensorOverview.sensor!!.name = "Frozen Food Temperature"
        sensorOverview.sensor!!.sensorLocation = SensorLocation()
        sensorOverview.sensor!!.sensorLocation!!.locationName = "Aisle ${sensorOverview.id}"
        save(sensorOverview)

        sensorOverview.id = "3"
        sensorOverview.sensor = Sensor()
        sensorOverview.sensor!!.name = "Juice Temperature"
        sensorOverview.sensor!!.sensorLocation = SensorLocation()
        sensorOverview.sensor!!.sensorLocation!!.locationName = "Aisle ${sensorOverview.id}"
        save(sensorOverview)

        sensorOverview.id = "4"
        sensorOverview.sensor = Sensor()
        sensorOverview.sensor!!.name = "Seafood Temperature"
        sensorOverview.sensor!!.sensorLocation = SensorLocation()
        sensorOverview.sensor!!.sensorLocation!!.locationName = "Aisle ${sensorOverview.id}"
        save(sensorOverview)

        sensorOverview.id = "5"
        sensorOverview.sensor = Sensor()
        sensorOverview.sensor!!.name = "Fresh Fruit Temperature"
        sensorOverview.sensor!!.sensorLocation = SensorLocation()
        sensorOverview.sensor!!.sensorLocation!!.locationName = "Aisle ${sensorOverview.id}"
        save(sensorOverview)

        sensorOverview.id = "6"
        sensorOverview.sensor = Sensor()
        sensorOverview.sensor!!.name = "Sushi Temperature"
        sensorOverview.sensor!!.sensorLocation = SensorLocation()
        sensorOverview.sensor!!.sensorLocation!!.locationName = "Aisle ${sensorOverview.id}"
        save(sensorOverview)


        sensorOverview.id = "7"
        sensorOverview.sensor = Sensor()
        sensorOverview.sensor!!.name = "Yoga/Cheese Temperature"
        sensorOverview.sensor!!.sensorLocation = SensorLocation()
        sensorOverview.sensor!!.sensorLocation!!.locationName = "Aisle ${sensorOverview.id}"
        save(sensorOverview)


        sensorOverview.id = "8"
        sensorOverview.sensor = Sensor()
        sensorOverview.sensor!!.name = "Frozen Food Temperature"
        sensorOverview.sensor!!.sensorLocation = SensorLocation()
        sensorOverview.sensor!!.sensorLocation!!.locationName = "Aisle ${sensorOverview.id}"
        save(sensorOverview)

    }

    private fun save(sensorOverview: SensorOverview) {

        if(sensorOverviewRegion.containsKeyOnServer(sensorOverview.id))
            return



        sensorOverviewRegion[sensorOverview.id] = sensorOverview
    }
}