import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`maven-publish`
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.github.ggreen"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	mavenLocal()
}

extra["springCloudVersion"] = "2021.0.3"

dependencies {

	implementation(project(":components:iot-event-domain"))
	implementation(project(":components:iot-event-repository"))
	implementation("org.springframework:spring-context")

	implementation("org.apache.geode:geode-core:1.15.0")
	implementation("org.apache.geode:geode-logging:1.15.0")
	implementation("org.apache.geode:geode-lucene:1.15.0")
	implementation("org.apache.geode:geode-wan:1.15.0")
	implementation("org.apache.geode:geode-cq:1.15.0")
	implementation("org.apache.geode:geode-common:1.15.0")
	implementation("org.apache.geode:geode-lucene:1.15.0")
	implementation("io.pivotal.services.dataTx:gemfire-extensions-core:1.1.1-SNAPSHOT")
	implementation("com.github.nyla-solutions:nyla.solutions.core:1.5.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("com.zaxxer:HikariCP:5.0.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
}
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	enabled = false
}

tasks.getByName<Jar>("jar") {
	enabled = true
}


dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
