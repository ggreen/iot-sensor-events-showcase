import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`maven-publish`
	kotlin("jvm") version "1.6.21"
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
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("com.github.nyla-solutions:nyla.solutions.core:1.5.0")
	testImplementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
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
