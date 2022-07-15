package com.github.ggreen.jdbc.batch.upsert.rabbit.stream.processor

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

/**
 * @author Gregory Green
 */
open class UpsertProcessor {
    private final var updateSql: String;
    private final var insertSql: String;
    private final var jdbcTemplate: NamedParameterJdbcTemplate;
    private val objectMapper = ObjectMapper();

    fun acceptJsonDataRow(json: String) {

        var dataRow: Map<String, *> = objectMapper.readValue(json, Map::class.java)
                as Map<String, *>;
        upsert(dataRow)
    }

    fun upsert(dataRow: Map<String, *>) {

        var cnt = jdbcTemplate.update(updateSql, dataRow);

        if (cnt == 0) {
            jdbcTemplate.update(insertSql, dataRow);
        }
    }//-------------------------------------------

    constructor (jdbcTemplate: NamedParameterJdbcTemplate,
                 @Value("\${app.updateSql}")
                 updateSql: String,
                 @Value("\${app.insertSql}")
                 insertSql: String) {
        this.jdbcTemplate = jdbcTemplate
        this.updateSql = updateSql
        this.insertSql = insertSql
    }
}