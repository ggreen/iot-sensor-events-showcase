package com.github.ggreen.rabbit.gemfire.sensor.analyzer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Gregory Green
 */
@SpringBootApplication
class GemFireStreamSensorAnalyzerApp

fun main(args: Array<String>) {
        runApplication<GemFireStreamSensorAnalyzerApp>(*args)
}
