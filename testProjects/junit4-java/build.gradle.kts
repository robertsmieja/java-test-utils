/**
 * Project that consumes our library in order to check compatibility with JUnit 4
 */

plugins {
    java
    eclipse
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    testImplementation(project(":"))
    testImplementation("junit:junit:4.13.2")
}

tasks.named<Test>("test") {
    useJUnit()
}
