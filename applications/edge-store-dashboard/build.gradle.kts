import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
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

	implementation(project(":components:iot-event-repository"))
	implementation(project(":components:iot-event-repository-gemfire"))

	implementation("io.pivotal.services.dataTx:gemfire-extensions-core:1.1.1-SNAPSHOT")

	implementation("org.apache.geode:geode-core:1.15.0")
	implementation("org.apache.geode:geode-logging:1.15.0")
	implementation("org.apache.geode:geode-lucene:1.15.0")
	implementation("org.apache.geode:geode-wan:1.15.0")
	implementation("org.apache.geode:geode-cq:1.15.0")
	implementation("org.apache.geode:geode-common:1.15.0")
	implementation("org.apache.geode:geode-lucene:1.15.0")


	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation(project(":components:iot-event-domain"))

	implementation("com.github.nyla-solutions:nyla.solutions.core:1.5.0")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.cloud:spring-cloud-stream")
	implementation("org.springframework.cloud:spring-cloud-stream-binder-rabbit")
	implementation("io.reactivex.rxjava3:rxjava:3.1.5")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
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
