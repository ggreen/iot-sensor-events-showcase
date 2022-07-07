package com.github.ggreen.iot.event.dashboard.repositories

import com.github.ggreen.iot.event.dashboard.domains.analytics.ConditionSummaries
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

/**
 * @author Gregory Green
 */
@Repository
class ConditionSummaryJdbcRepository(private val template: JdbcTemplate) : ConditionSummaryRepository  {
    private val sql : String = """
        select (select count(id) from sensor_record) total_count,
       (select count(id)  from sensor_record where cast(data#>'{priority}' as int) = 0) normal_count,
       (select count(id)  from sensor_record where cast(data#>'{priority}' as int) = 1) warning_count,
       (select count(id)  from sensor_record where cast(data#>'{priority}' as int) > 1) severe_count
    """.trimIndent()

    override fun findTotalConditionSummaries(): ConditionSummaries? {
        val extractor : (ResultSet) -> ConditionSummaries? =
            {  rs : ResultSet ->

                if(!rs.next())
                    null;
                else
                {
                    ConditionSummaries(totalCount = rs.getInt(1),
                        normalCount = rs.getInt(2),
                        warningCount = rs.getInt(3),
                        severeCount = rs.getInt(4))
                }

        }
        return template.query(sql,extractor)
    }
}