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
                    null
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

    /**
     * @return the summaries with normal, warning and severe totals
     */
    override fun findConditionSummariesGroupNyName(): Iterable<ConditionSummaries>? {

        val sql = """
            select
              totals_sensor_name.label label,
              (select count(*) from sensor_record where  data#>>'{sensor,name}' = totals_sensor_name.label and data#>>'{priority}' = '0') normal_count,
              (select count(*) from sensor_record where  data#>>'{sensor,name}' = totals_sensor_name.label and data#>>'{priority}' = '1') warning_count,
              (select count(*) from sensor_record where  data#>>'{sensor,name}' = totals_sensor_name.label and cast(data#>>'{priority}' as int) > 1) severe_count
            from
                (select count(*) total_count,data#>>'{sensor,name}' label   
                from sensor_record  group by  data#>>'{sensor,name}') totals_sensor_name
            order by label;
        """.trimIndent()

        val rowMapper = { rs:ResultSet, _:Int ->
            ConditionSummaries( label = rs.getString("label"),
                                normalCount =  rs.getInt("normal_count"),
                                warningCount = rs.getInt("warning_count"),
                                severeCount = rs.getInt("severe_count")
            )}

            return template.query(sql,rowMapper)
    }


}