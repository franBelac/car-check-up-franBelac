plugins {
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.spring") version "1.7.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("org.jlleitschuh.gradle.ktlint-idea") version "10.3.0"

    id("org.springframework.boot") version "2.7.1" // Defines version of Spring Boot
    id("io.spring.dependency-management") version "1.0.11.RELEASE" // Handles Spring
    id("org.unbroken-dome.test-sets") version "4.0.0"

    kotlin("plugin.jpa") version "1.7.10"
}

group = "com.infinum.course"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

extra["testcontainersVersion"] = "1.15.3"

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.springframework:spring-context:5.3.20")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.flywaydb:flyway-core")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("org.springframework:spring-test:5.3.20")
    testImplementation("org.testcontainers:postgresql")

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    testImplementation("org.mock-server:mockserver-spring-test-listener:5.13.2")

    implementation("org.springframework.boot:spring-boot-starter-hateoas")

}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

testSets {
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootJar {
    dependsOn(tasks.ktlintCheck)
    dependsOn(tasks.test)
}