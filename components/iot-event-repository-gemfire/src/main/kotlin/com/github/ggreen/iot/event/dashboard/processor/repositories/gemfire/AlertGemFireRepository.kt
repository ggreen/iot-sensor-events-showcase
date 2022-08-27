package com.github.ggreen.iot.event.dashboard.processor.repositories.gemfire

import com.github.ggreen.iot.event.dashboard.processor.repositories.AlertRepository
import com.vmware.data.services.gemfire.io.QuerierService
import org.springframework.stereotype.Component

/**
 * @author Gregory Green
 */
@Component
class AlertGemFireRepository(private val querierService: QuerierService) : AlertRepository {
    override fun count(): Long? {

        var results = querierService.query<Long>("select sum(alarmCount) from /sensorOverview")

        if(results == null || results.isEmpty())
            return 0

        return results.iterator().next().toLong()
    }
}