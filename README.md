Java Test Utils
===============

[![Download](https://api.bintray.com/packages/robertsmieja/maven/java-test-utils/images/download.svg) ](https://bintray.com/robertsmieja/maven/java-test-utils/_latestVersion)
[![Build Status](https://travis-ci.org/robertsmieja/java-test-utils.svg?branch=master)](https://travis-ci.org/robertsmieja/java-test-utils)
[![codecov](https://codecov.io/gh/robertsmieja/java-test-utils/branch/master/graph/badge.svg)](https://codecov.io/gh/robertsmieja/java-test-utils)
[![Join the chat at https://gitter.im/java-test-utils/Lobby](https://badges.gitter.im/java-test-utils/Lobby.svg)](https://gitter.im/java-test-utils/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This project is intended to serve as a set of common utilities used in testing, between my personal project.

It currently consists of a set of utilities to simplify unit testing of all getters/setters, equals(), hashCode(), and toString() methods.
### Usage
#### Requirements
* Java 1.8 or newer
* Apache Commons java.lang3 at runtime
* Apache Commons Collections 4 runtime
* JUnit 5 API at compile time

The library is currently available on JCenter at the following [repository](https://bintray.com/robertsmieja/maven/java-test-utils).

There is no current stable release yet, but I've published a "milestone" release that should be usable.
See [this unit test](src/test/java/com/robertsmieja/test/utils/junit/SimplePojoTests.java) as an example of how to use the current implementation.

The library should be compatible with JUnit 4 projects, but is not fully fleshed our for those.
See [this example JUnit 4 project](testProjects/junit4).

Currently the Equals, HashCode, and ToString tests are lacking JUnit 4 compatibility.

### Development Requirements:
* Java 1.8 or newer
* JUnit 5.0.0-M4 or newer
* IDE support for JUnit 5 (Currently only IntelliJ IDEA)

### Issues
If there are any bugs, or ideas on how to improve these classes, please open a new GitHub issue.

### License
The code is licensed under Apache 2.0. 
If you find it useful, I'd love to know.