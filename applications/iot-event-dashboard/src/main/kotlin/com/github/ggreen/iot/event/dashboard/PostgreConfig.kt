package com.github.ggreen.iot.event.dashboard

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


/**
 * @author Gregory Green
 */
//@Configuration
////@EnableJpaRepositories
//class PostgreConfig {
//
//    @Bean
//    @ConfigurationProperties("app.datasource")
//    fun dataSource(): HikariDataSource? {
//        return DataSourceBuilder
//            .create().type(HikariDataSource::class.java).build()
//    }
//}