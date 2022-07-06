//package com.github.ggreen.iot.event.dashboard
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import com.github.ggreen.iot.event.dashboard.support.CustomMappingR2dbcConverter
//import com.github.ggreen.iot.event.dashboard.support.JsonToSensorRecord
//import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
//import io.r2dbc.postgresql.PostgresqlConnectionFactory
//import io.r2dbc.spi.ConnectionFactory
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.convert.converter.Converter
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
//import org.springframework.data.r2dbc.convert.MappingR2dbcConverter
//import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
//import org.springframework.data.r2dbc.mapping.R2dbcMappingContext
//import org.springframework.jdbc.core.JdbcTemplate
//import javax.sql.DataSource
//
///**
// * @author Gregory Green
// */
//@Configuration
////@EnableR2dbcRepositories
////@EnableJdbcRepositories
//@EnableJpaRepositories
//class PostgresR2dcConfig : AbstractR2dbcConfiguration() {
//
//    @Value("\${postgres.username}")
//    private lateinit var username: String
//
//    @Value("\${postgres.password}")
//    private lateinit var password: String
//
//    @Value("\${postgres.database}")
//    private lateinit var database: String
//
//    @Value("\${postgres.host}")
//    private lateinit var host: String
//
//    @Bean
//    fun jdbcTemplate(dataSource: DataSource): JdbcTemplate {
//        return JdbcTemplate(dataSource)
//    }
//
//    @Bean
//    override fun connectionFactory() : ConnectionFactory
//    {
//        var connectionFactory = PostgresqlConnectionFactory(
//            PostgresqlConnectionConfiguration.builder()
//            .host(host)
//            .database(database)
//        .username(username)
//        .password(password).build());
//
//        return connectionFactory
//    }
//
//
//    @Bean
//    override fun r2dbcConverter(
//        mappingContext: R2dbcMappingContext,
//        r2dbcCustomConversions: R2dbcCustomConversions
//    ): MappingR2dbcConverter {
//
//        return CustomMappingR2dbcConverter(mappingContext!!)
//       // return MappingR2dbcConverter(mappingContext!!, r2dbcCustomConversions()!!)
//    }
//
//        @Bean
//        override fun r2dbcCustomConversions(): R2dbcCustomConversions {
//            var objectMapper = ObjectMapper()
//            val converters: MutableList<Converter<*, *>?> = ArrayList<Converter<*, *>?>()
//            converters.add(JsonToSensorRecord(objectMapper))
//            return R2dbcCustomConversions(storeConversions, converters)
//        }
//}