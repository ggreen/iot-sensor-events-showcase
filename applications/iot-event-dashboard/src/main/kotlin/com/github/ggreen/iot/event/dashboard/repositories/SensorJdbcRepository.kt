package com.github.ggreen.iot.event.dashboard.repositories

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSet

/**
 * @author Gregory Green
 */
@Service
class SensorJdbcRepository(private val template: JdbcTemplate) : SensorRepository {
    private val objectMapper = ObjectMapper()

    override fun findSensorOverviews(): Iterable<SensorOverview> {
        val rowMapper : (ResultSet, Int) -> SensorOverview =
            {
                    rs : ResultSet, id : Int ->  objectMapper.readValue(rs.getString("data"),SensorOverview::class.java)
            }

        return template.query("select * from sensor_record",rowMapper)
    }
}