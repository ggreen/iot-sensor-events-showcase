import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.github.ggreen"
version = "0.0.2-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	mavenLocal()
}

extra["springCloudVersion"] = "2021.0.3"

dependencies {

	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	implementation("com.rabbitmq:stream-client-sac:0.6.0-SNAPSHOT")
	implementation("com.github.nyla-solutions:nyla.solutions.core:1.5.0")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.pivotal.services.dataTx:gemfire-extensions-core:1.1.0")
	implementation("org.apache.geode:geode-core:1.15.0")
	implementation("org.apache.geode:geode-logging:1.15.0")
	implementation("org.apache.geode:geode-lucene:1.15.0")
	implementation("org.apache.geode:geode-wan:1.15.0")
	implementation("org.apache.geode:geode-cq:1.15.0")
	implementation("org.apache.geode:geode-common:1.15.0")
	implementation("org.apache.geode:geode-lucene:1.15.0")



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
//		mavenBom("org.springframework.boot:spring-cloud-dependencies:${property("springCloudVersion")}")
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

repositories {
//	maven { url "https://packagecloud.io/rabbitmq/maven-milestones/maven2" }
	mavenLocal()
	mavenCentral()
}