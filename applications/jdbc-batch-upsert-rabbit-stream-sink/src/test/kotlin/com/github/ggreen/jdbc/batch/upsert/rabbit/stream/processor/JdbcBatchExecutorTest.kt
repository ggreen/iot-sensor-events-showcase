package com.github.ggreen.jdbc.batch.upsert.rabbit.stream.processor

import com.github.ggreen.jdbc.batch.upsert.rabbit.stream.config.SqlProperties
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

internal class JdbcBatchExecutorTest{

    private lateinit var sqls: SqlProperties
    private lateinit var template: NamedParameterJdbcTemplate

    @BeforeEach
    internal fun setUp() {

        template = mock()
        sqls = SqlProperties(sql = arrayOf(
            "insert into table1",
            "update table1 set something",
        ))
    }

    @Test
    internal fun given_bytePayload_whenEvent_thenExecuteBatch() {
        var subject = JdbcBatchExecutor(template,sqls)
        val expected = sqls.sql.size

        val payload = """
            { 
                "id": "11",
                "name" : "Imani"
             }
            """.toByteArray(Charsets.UTF_8)

        var actual = subject.executeBatch(payload)

        assertEquals(expected,actual.size);



    }
}