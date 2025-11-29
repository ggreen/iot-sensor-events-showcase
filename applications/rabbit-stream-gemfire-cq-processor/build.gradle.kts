plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
    id("org.springframework.boot") version "4.0.0"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "2.2.21"
}

group = "com.github.ggreen"
version = "0.0.2-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
	mavenCentral()
	mavenLocal()
}

extra["springCloudVersion"] = "2025.1.0"

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

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}


repositories {
//	maven { url "https://packagecloud.io/rabbitmq/maven-milestones/maven2" }
	mavenLocal()
	mavenCentral()
}