// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    id("maven-publish")
    id("signing")
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

val ossrhUsername: String = System.getenv("OSSRH_USERNAME")
    ?: project.findProperty("OSSRH_USERNAME") as? String
    ?: error("OSSRH_USERNAME not found.")

val ossrhPassword: String = System.getenv("OSSRH_PASSWORD")
    ?: project.findProperty("OSSRH_PASSWORD") as? String
    ?: error("OSSRH_PASSWORD not found.")

nexusPublishing {
    repositories {
        sonatype {
            username.set(ossrhUsername)
            password.set(ossrhPassword)
        }
    }
}