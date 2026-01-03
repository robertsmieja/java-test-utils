Java Test Utils
===============

[![CI](https://github.com/robertsmieja/java-test-utils/workflows/CI/badge.svg)](https://github.com/robertsmieja/java-test-utils/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/robertsmieja/java-test-utils/branch/master/graph/badge.svg)](https://codecov.io/gh/robertsmieja/java-test-utils)

This project is intended to serve as a set of common utilities used in testing, between my personal projects.

It currently consists of a set of utilities to simplify unit testing of all getters/setters, equals(), hashCode(), and toString() methods.

### Usage
#### Requirements
* Java 21 or newer
* Apache Commons Lang3 at runtime
* Apache Commons Collections 4 at runtime
* JUnit 5 API at compile time

There is no current stable release yet, but milestone releases are planned.
See [this unit test](src/test/java/com/robertsmieja/test/utils/junit/SimplePojoTests.java) as an example of how to use the current implementation.

The library is compatible with JUnit 4 projects.
See [this example JUnit 4 Java project](testProjects/junit4-java).

### Development Requirements:
* Java 21 or newer
* Gradle 8.12 or newer (with Kotlin DSL build scripts)
* JUnit 5 (latest)
* IDE support for JUnit 5 (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Building
```bash
./gradlew build
```

### Testing
```bash
./gradlew test
```

### Code Coverage
```bash
./gradlew jacocoTestReport
```

The coverage report will be available at `build/reports/jacoco/test/html/index.html`.

### CI/CD
This project uses GitHub Actions for continuous integration and deployment:
- **CI Workflow**: Runs on every push and pull request, testing on Java 21, 22, and 23
- **Publish Workflow**: Automatically publishes releases to Maven Central
- **Dependabot**: Keeps dependencies up to date automatically

### Issues
If there are any bugs, or ideas on how to improve this library, please open a new GitHub issue.

### License
The code is licensed under Apache 2.0. 
If you find it useful, I'd love to know.