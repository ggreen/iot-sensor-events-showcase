package com.github.ggreen.jdbc.batch.upsert.rabbit.stream


import com.fasterxml.jackson.core.JsonParseException
import com.github.ggreen.jdbc.batch.upsert.rabbit.stream.processor.UpsertProcessor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.*
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

/**
 * @author Gregory Green
 */
internal class UpsertProcessorTest {

    lateinit var jdbcTemplate : NamedParameterJdbcTemplate;

    @BeforeEach
    fun setUp() {
        jdbcTemplate  = mock();
    }

    @Test
    internal fun acceptJsonDataRow_empty() {
        var subject = UpsertProcessor(jdbcTemplate, "", "")

        val emptyObject = "{}";

        whenever(jdbcTemplate.update(anyString(),any<Map<String, Any>>())).thenReturn(1)

        subject.acceptJsonDataRow(emptyObject)

        verify(jdbcTemplate).update(anyString(),any<Map<String, Any>>())

    }

    @Test
    internal fun acceptJsonDataRow_invalid() {
        var subject = UpsertProcessor(jdbcTemplate, "", "")

        val invalid = "{";
        assertThrows<JsonParseException> {
            subject.acceptJsonDataRow(invalid)
        }
    }

    @Test
    internal fun test_update() {

        var dataRow :Map<String,*> = mapOf("col1" to "dsds")

        val updateSql = "insert into table values(?,?)";

        var subject = UpsertProcessor(jdbcTemplate, updateSql, "")
        whenever(jdbcTemplate.update(updateSql,dataRow)).thenReturn(1)

        subject.upsert(dataRow);

        verify(jdbcTemplate).update(updateSql, dataRow)
    }

    @Test
    internal fun test_insert() {

        var dataRow = mapOf("col1" to "dsds" as Object)

        var insertSql = "insert into table values(:col1)";
        var updateSql = "update table set values cl=:col1";

        var jdbcTemplate = mock<NamedParameterJdbcTemplate>()


        var subject = UpsertProcessor(jdbcTemplate, updateSql, insertSql)

        whenever(jdbcTemplate.update(updateSql,dataRow)).thenReturn( 0)
        whenever( jdbcTemplate.update(insertSql,dataRow)).thenReturn( 1)


        subject.upsert(dataRow);

        verify(jdbcTemplate, times(1)).update(insertSql, dataRow)

    }
}