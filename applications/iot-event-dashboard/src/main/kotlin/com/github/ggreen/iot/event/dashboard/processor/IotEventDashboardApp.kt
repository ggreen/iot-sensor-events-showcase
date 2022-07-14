package com.github.ggreen.iot.event.dashboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IotEventDashboardApp

fun main(args: Array<String>) {
	runApplication<IotEventDashboardApp>(*args)
}
