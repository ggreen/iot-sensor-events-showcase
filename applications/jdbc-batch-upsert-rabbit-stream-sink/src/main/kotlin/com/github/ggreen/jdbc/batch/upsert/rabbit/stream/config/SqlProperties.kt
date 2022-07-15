package com.github.ggreen.jdbc.batch.upsert.rabbit.stream.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "stream")
data class SqlProperties(var sql : Array<String>)
