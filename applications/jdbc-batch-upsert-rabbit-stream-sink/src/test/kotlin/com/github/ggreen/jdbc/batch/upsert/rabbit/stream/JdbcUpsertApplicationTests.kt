//package com.github.ggreen.jdbc.batch.upsert.rabbit.stream
//
//import nyla.solutions.core.io.IO
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.boot.web.server.LocalServerPort
//import org.springframework.cloud.stream.binder.test.InputDestination
//import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration
//import org.springframework.context.annotation.Import
//import org.springframework.jdbc.core.JdbcTemplate
//import org.springframework.kafka.test.context.EmbeddedKafka
//import org.springframework.messaging.support.MessageBuilder
//import java.nio.file.Paths
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        properties = arrayOf("local.server.port=8080",
//                "app.updateSql=update blackat.members set MEMBER_NM = :name where MEMBER_ID =:id",
//                "app.insertSql=insert into blackat.members(MEMBER_ID,MEMBER_NM) values(:id,:name)"
////                "spring.cloud.stream.bindings.default-binder=kafka",
////                "spring.cloud.stream.bindings.default-binder=kafka",
////                "spring.cloud.stream.binders.binderName.type=kafka",
////        "spring.cloud.stream.bindings.input.binder=kafka",
////                "spring.cloud.stream.bindings.output.binder=kafka"
//        )
//)
////@EmbeddedKafka
////@Import(TestChannelBinderConfiguration::class)
//class JdbcUpsertApplicationTests {
//
//    @LocalServerPort
//    private val port = 0
//
//    @Autowired
//    lateinit var restTemplate: TestRestTemplate
//
//
////    @Autowired
////    lateinit var input: InputDestination
//
//    @Autowired
//    lateinit var jdbcTemplate: JdbcTemplate
//
//
//    @BeforeEach
//    internal fun setUp() {
//        var sql = IO.readFile(Paths.get("src/test/resources/h2_test_ddl.sql").toFile())
//        jdbcTemplate.execute(sql);
//
//        jdbcTemplate.execute("delete from blackAt.Members")
//    }
//
//    @Test
//    internal fun actuatorInfo() {
//        ///actuator/info
//        assertThat(this.restTemplate.getForEntity(
//                "http://localhost:" + port +
//                        "/actuator/info",
//                String::class.java).statusCodeValue)
//                .isEqualTo(200)
//    }
//
//    @Test
//    internal fun insert() {
//        var json = "{\"id\": \"1\", \"name\": \"Hello\"}";
//        var msg = MessageBuilder.withPayload(json)
//                .build();
//
//        input.send(msg);
//
//        var cnt = jdbcTemplate.queryForObject(
//                "select count(*) from blackAt.Members",
//                Integer::class.java);
//
//        assertThat(cnt).satisfies { cnt -> cnt!! > 1 }
//    }
//
//    @Test
//    internal fun updates() {
//
//        var json = "{\"id\": \"2\", \"name\": \"Before\"}";
//        var msg = MessageBuilder.withPayload(json)
//                .build();
//
//        input.send(msg);
//
//        var name = jdbcTemplate.queryForObject(
//                "select MEMBER_NM from blackAt.Members",
//                String::class.java);
//
//        assertEquals("Before", name);
//
//
//        json = "{\"id\": \"2\", \"name\": \"After\"}";
//        msg = MessageBuilder.withPayload(json)
//                .build();
//        input.send(msg);
//
//        name = jdbcTemplate.queryForObject(
//                "select MEMBER_NM from blackAt.Members",
//                String::class.java);
//
//        assertThat(name).isEqualTo("After");
//
//    }
//}
