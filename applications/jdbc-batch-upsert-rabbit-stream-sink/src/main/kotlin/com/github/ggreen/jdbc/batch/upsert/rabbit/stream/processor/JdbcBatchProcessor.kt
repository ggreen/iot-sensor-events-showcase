package com.github.ggreen.jdbc.batch.upsert.rabbit.stream.processor

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.ggreen.jdbc.batch.upsert.rabbit.stream.config.SqlProperties
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component


/**
 * Execute a array of SQL statements and returns the returns of each
 *
 *
 * @author Gregory Green
 */
@Component
class JdbcBatchProcessor(private val template: NamedParameterJdbcTemplate, private val sqls: SqlProperties) {

    private val objectMapper = ObjectMapper()

    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * Execute SQLs in batch using the NamedParameterJdbcTemplate for the map
     * version of the JSON binary payload
     */
    fun executeBatch(payloadJson: ByteArray): List<Int> {
        var map : Map<String, Any> = objectMapper.readValue(payloadJson,Map::class.java) as Map<String, Any>

        log.info("Input MAP:$map")

        var returns : MutableList<Int> = mutableListOf<Int>()
        for(sql in sqls.sql)
        {
            log.info("Executing SQL $sql")
            var result = template.update(sql,map)

            log.info("Adding result $result")
            returns.add(result)
        }

        return returns
    }
}