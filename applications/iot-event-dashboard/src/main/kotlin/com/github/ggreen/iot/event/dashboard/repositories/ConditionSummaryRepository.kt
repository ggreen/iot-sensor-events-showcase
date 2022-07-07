package com.github.ggreen.iot.event.dashboard.repositories

import com.github.ggreen.iot.event.dashboard.domains.analytics.ConditionSummaries

interface ConditionSummaryRepository {
    fun findTotalConditionSummaries() : ConditionSummaries?

     fun findConditionSummariesGroupNyName(): Iterable<ConditionSummaries>?
}