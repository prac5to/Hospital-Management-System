# Upgrade Plan: hopital (20261008065113)

- **Generated**: 2026-10-08 06:51 UTC
- **HEAD Branch**: N/A
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 25: /Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home/bin (target runtime; available)
- JDK 27: /opt/homebrew/Cellar/openjdk/27/libexec/openjdk.jdk/Contents/Home/bin (available, not required for this upgrade)

**Build Tools**
- Maven Wrapper: 3.9.16 (in .mvn/wrapper/maven-wrapper.properties; already compatible with Java 25)
- Maven installation: /opt/homebrew/Cellar/maven/3.9.14/bin (compatible with Java 25)

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

- Upgrade the project to the latest LTS runtime available on the machine, targeting Java 25.
- Prefer a minimal change set that preserves the existing Spring Boot 3.5.x stack.
- Keep the build deterministic by using the project wrapper when available.

## Options

- Working branch: appmod/java-upgrade-20261008065113
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java 25 (latest LTS runtime available)

## Technology Stack

| Technology/Dependency | Current | Min Compatible Version | Why Incompatible |
| ---------------------- | ------- | ---------------------- | ---------------- |
| Java | 17 | 25 | User requested Java 25 runtime upgrade |
| Spring Boot | 3.5.16 | 3.5.16 | Already compatible with Java 25; no framework bump needed |
| Maven Wrapper | 3.9.16 | 3.9.16 | Already compatible with Java 25 |
| Spring Security | 6.x (managed by Spring Boot 3.5) | 6.x | Spring Boot 3.5 already uses the compatible security model for Java 25 |
| Jakarta EE / JPA | 3.x | 3.x | Already aligned with Spring Boot 3.5, no namespace migration required |

## Derived Upgrades

- Java 25 runtime is supported by the existing Spring Boot 3.5.16 dependency set; the codebase already uses the Jakarta namespace and Spring Security 6.x.
- No intermediate framework upgrade is necessary because the project is already on the current stable Spring Boot line.
- Set the project Java target to 25 via the Maven property and validate compilation and tests under the JDK 25 toolchain.

## Impact Analysis

### Subsection: Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|-----------|---------|--------|--------|--------|
| pom.xml | java.version | 17 | upgrade | 25 | User requested Java 25 runtime |
| pom.xml | spring-boot-starter-parent | 3.5.16 | keep | 3.5.16 | Already compatible with Java 25; no framework change needed |
| .mvn/wrapper/maven-wrapper.properties | distributionUrl | 3.9.16 | keep | 3.9.16 | Maven wrapper already supports Java 25 |

### Subsection: Source Code Changes

| File | Location | Current | Required Change | Reason |
|------|----------|---------|-----------------|--------|
| No source file changes expected | Application codebase | Spring Boot 3.5 + Jakarta imports | none required | The project already targets the Jakarta ecosystem and does not use removed JDK internals |

### Subsection: Configuration Changes

| File | Property/Setting | Current | Required Change | Reason |
|------|------------------|---------|-----------------|--------|
| pom.xml | java.version | 17 | set to 25 | Required for Java 25 toolchain |

### Subsection: CI/CD Changes

| File | Location | Current | Required Change |
|------|----------|---------|----------------|
| none identified | project build files | default JDK settings | no CI/CD file hardcoded to Java 17 was found |

### Subsection: Risks & Warnings

- **Java runtime update is low-risk because the project already uses Spring Boot 3.5.16 and jakarta.* imports.** Mitigation: verify compilation and the test suite with JDK 25 after changing the Maven target level.
- **No direct JDK-internal reflection or removed package usage was found in the search.** Mitigation: keep the change set minimal and rely on compilation/test validation to catch runtime issues.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Confirm the required JDK and Maven toolchain is installed before changing the project target.
  - **Changes to Make**: Validate Java 25 is available and ready for use with the wrapper build.
  - **Verification**: `./mvnw -version` with `JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home` and JDK 25 path check.

- Step 2: Setup Baseline
  - **Rationale**: Capture the current state before changing the Java runtime target. The base JDK is not present on this machine, so this step will be skipped per the upgrade policy.
  - **Changes to Make**: None; record baseline skip due to missing legacy JDK.
  - **Verification**: Skip baseline compile/test because current JDK 17 is unavailable on the system.

- Step 3: Upgrade the project Java target to 25
  - **Rationale**: This is the required runtime change; Spring Boot 3.5.16 already supports Java 25, so no framework migration is necessary.
  - **Changes to Make**: Update the Java target property in pom.xml to 25 and rebuild under the JDK 25 toolchain.
  - **Verification**: `JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home ./mvnw -q -DskipTests test-compile` and then full `./mvnw -q test` after validation.

- Step 4: Final Validation
  - **Rationale**: Confirm the upgraded project compiles and all automated tests pass on Java 25.
  - **Changes to Make**: Resolve any compatibility issues discovered during test execution, then re-run the full suite.
  - **Verification**: `JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home ./mvnw -q test` with a 100% pass target.
