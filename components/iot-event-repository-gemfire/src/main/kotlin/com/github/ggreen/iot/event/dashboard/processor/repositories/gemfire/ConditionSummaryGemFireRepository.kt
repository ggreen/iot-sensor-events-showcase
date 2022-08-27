package com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire

import com.github.ggreen.iot.event.dashboard.domains.analytics.ConditionSummaries
import com.github.ggreen.iot.event.dashboard.domains.sensor.SensorOverview
import com.github.ggreen.iot.event.dashboard.repositories.ConditionSummaryRepository
import com.vmware.data.services.gemfire.io.QuerierService
import org.apache.geode.cache.Region
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * @author Gregory Green
 */
@Component
class ConditionSummaryGemFireRepository(
    @Qualifier("summariesGroupNyName")
    private val summariesGroupNyNameRegion: Region<String, ConditionSummaries>,
    private val sensorOverviewRegion: Region<String, SensorOverview>,
    private val queryService: QuerierService
) : ConditionSummaryRepository {

    private val summeryTotalKey ="total"

    override fun findTotalConditionSummaries(): ConditionSummaries? {

        val keys = sensorOverviewRegion.keySetOnServer()
        var totalCount = 0
        var warningCount = 0
        var severeCount = 0

        if(keys != null)
            totalCount = keys.count()

        val warningCountsList = queryService.query<Int>("select count(*) from /sensorOverview where priority = 1")
        if(warningCountsList!= null && !warningCountsList.isEmpty())
            warningCount = warningCountsList.iterator().next()

        val severeCountsList = queryService.query<Int>("select count(*) from /sensorOverview where priority = 2")
        if(severeCountsList!= null && !severeCountsList.isEmpty())
            severeCount = severeCountsList.iterator().next()


        return ConditionSummaries(
            label = summeryTotalKey,
            totalCount=totalCount,
            normalCount = totalCount - (warningCount+severeCount),
            warningCount = warningCount,
            severeCount = severeCount
        )
    }

    override fun findConditionSummariesGroupNyName(): Iterable<ConditionSummaries>? {
        val ketSet = summariesGroupNyNameRegion.keySetOnServer()
        if(ketSet == null || ketSet.isEmpty())
            return null

        val entries = summariesGroupNyNameRegion.getAll(ketSet)
        if(entries == null || entries.isEmpty())
            return null

        return entries.values
    }
}