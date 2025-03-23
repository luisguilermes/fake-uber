import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "fake.uber"
version = "0.0.1"

plugins {
    // Apply kotlin plugin
    alias(libs.plugins.kotlin)
    // Apply ktlint plugin for code formatting
    alias(libs.plugins.ktlint)
    // Apply test logger plugin for better test logging
    alias(libs.plugins.test.logger)
    // Apply jacoco plugin for code coverage
    jacoco
}

allprojects {
    // Apply necessary plugins to all projects
    apply(plugin = "kotlin")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "com.adarshr.test-logger")
    apply(plugin = "jacoco")

    repositories {
        // Define repositories for dependencies
        mavenCentral()
        google()
    }

    tasks.withType<KotlinCompile> {
        // Configure Kotlin compiler options
        compilerOptions {
            freeCompilerArgs.set(listOf("-Xjsr305=strict"))
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    configure<TestLoggerExtension> {
        // Configure Test Logger plugin
        theme = ThemeType.MOCHA
        showExceptions = true
    }

    tasks.withType<Test> {
        // Use JUnit Platform for testing
        useJUnitPlatform()
        testLogging {
            // Configure test logging
            showStandardStreams = true
            events("passed", "skipped", "failed")
        }

        // Generate Jacoco test report after tests
        finalizedBy(tasks.jacocoTestReport)
    }

    tasks.jacocoTestReport {
        // Configure Jacoco test report
        dependsOn(tasks.test)
        reports {
            xml.required = true
            html.required = true
        }
    }

    tasks.jacocoTestCoverageVerification {
        // Configure Jacoco test coverage verification
        violationRules {
            rule {
                limit {
                    minimum = 0.80.toBigDecimal()
                }
            }
        }
    }

    tasks.check {
        // Ensure all checks are run
        dependsOn(tasks.test)
        dependsOn(tasks.jacocoTestCoverageVerification)
        dependsOn(tasks.ktlintCheck)
    }
}

subprojects {
    // Ensure Ktlint format is run before build
    tasks.find { it.name == "build" }?.dependsOn(tasks.named("ktlintFormat"))
}