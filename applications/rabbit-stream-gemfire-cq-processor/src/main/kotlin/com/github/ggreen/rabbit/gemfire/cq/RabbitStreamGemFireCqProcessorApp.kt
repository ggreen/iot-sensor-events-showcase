package com.github.ggreen.rabbit.gemfire.cq

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Gregory Green
 */
@SpringBootApplication
class RabbitStreamGemFireCqProcessor

fun main(args: Array<String>) {
        runApplication<RabbitStreamGemFireCqProcessor>(*args)
}
