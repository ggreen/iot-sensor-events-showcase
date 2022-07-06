package com.github.ggreen.iot.event.dashboard.domains.analytics

data class ConditionSummaries(
    val label : String = "",
    val totalCount : Int = 0,
    val normalCount: Int = 0,
    var warningCount: Int = 0,
    val severeCount: Int = 0)
