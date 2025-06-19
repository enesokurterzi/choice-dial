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


nexusPublishing {
    repositories {
        sonatype {
            username.set(project.findProperty("OSSRH_USERNAME") as String? ?: System.getenv("OSSRH_USERNAME"))
            password.set(project.findProperty("OSSRH_PASSWORD") as String? ?: System.getenv("OSSRH_PASSWORD"))
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
        }
    }
}