package com.github.ggreen.jdbc.batch.upsert.rabbit.stream

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JdbcBatchUpsertRStreamApp

fun main(args: Array<String>) {
	runApplication<JdbcBatchUpsertRStreamApp>(*args)
}
