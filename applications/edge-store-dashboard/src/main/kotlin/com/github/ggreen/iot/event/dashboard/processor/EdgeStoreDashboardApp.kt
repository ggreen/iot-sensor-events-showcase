package com.github.ggreen.iot.event.dashboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EdgeStoreDashboardApp

fun main(args: Array<String>) {
	runApplication<EdgeStoreDashboardApp>(*args)
}
