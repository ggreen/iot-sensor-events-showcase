package com.github.ggreen.rabbit.gemfire.filter.oql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Gregory Green
 */
@SpringBootApplication
class GemFireSinkStreamApp

fun main(args: Array<String>) {
        runApplication<GemFireSinkStreamApp>(*args)
}
