package com.github.ggreen.iot.event.dashboard.processor.repositories

interface AlertRepository {
    fun count() : Long?
}