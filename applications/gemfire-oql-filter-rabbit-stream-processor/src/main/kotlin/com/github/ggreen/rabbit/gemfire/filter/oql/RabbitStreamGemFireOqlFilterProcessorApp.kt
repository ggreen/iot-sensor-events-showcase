package com.github.ggreen.rabbit.gemfire.filter.oql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Gregory Green
 */
@SpringBootApplication
class RabbitStreamGemFireOqlFilterProcessorApp

fun main(args: Array<String>) {
        runApplication<RabbitStreamGemFireOqlFilterProcessorApp>(*args)
}
