/**
 * Project that consumes our library in order to check compatibility with JUnit 4 and Groovy
 */

plugins {
    groovy
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
    implementation("org.apache.groovy:groovy:4.0.24")
    testImplementation(project(":"))
    testImplementation("junit:junit:4.13.2")
}

tasks.named<Test>("test") {
    useJUnit()
}
