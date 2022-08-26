package com.github.ggreen.iot.event.dashboard.processor.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

/**
 * The Alert JDBC Repository
 * @author Gregory Green
 */
@Repository
class AlertJdbcRepository(private val template: JdbcTemplate) : AlertRepository {
    private var sql = "select sum((data->>'alarmCount')::int) from sensor_record"

    override fun count(): Long? {
        return template.queryForObject(sql, Long::class.java)
    }
}