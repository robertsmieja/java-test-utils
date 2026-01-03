plugins {
    `java-library`
    `maven-publish`
    eclipse
    jacoco
}

group = "com.robertsmieja"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("org.apache.commons:commons-collections4:4.5.0-M2")
    implementation("org.junit.jupiter:junit-jupiter-api:5.11.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.0.1")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named<JavaCompile>("compileJava") {
    options.compilerArgs.add("-Xlint:all")
    options.compilerArgs.add("-Xlint:-processing")
}

/* Code Coverage */
jacoco {
    toolVersion = "0.8.12"
}

tasks.named<JacocoReport>("jacocoTestReport") {
    reports {
        xml.required = true
        csv.required = false
        html.required = true
    }
}

tasks.named("check") {
    dependsOn(tasks.named("jacocoTestReport"))
}

/* Publishing configuration */
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name = "Java Test Utils"
                description = "A set of common utilities for testing Java code with JUnit"
                url = "https://github.com/robertsmieja/${project.name}"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "robertsmieja"
                        name = "Robert Smieja"
                        email = "robertsmieja@robertsmieja.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/robertsmieja/${project.name}.git"
                    developerConnection = "scm:git:ssh://github.com:robertsmieja/${project.name}.git"
                    url = "https://github.com/robertsmieja/${project.name}"
                }
            }
        }
    }
}
