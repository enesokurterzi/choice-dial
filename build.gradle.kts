// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.maven.publish) // nexus publish plugin
}

val ossrhUsername: String? = project.findProperty("OSSRH_USERNAME") as? String ?: System.getenv("OSSRH_USERNAME")
val ossrhPassword: String? = project.findProperty("OSSRH_PASSWORD") as? String ?: System.getenv("OSSRH_PASSWORD")

nexusPublishing {
    repositories {
        sonatype {
            username.set(ossrhUsername ?: error("OSSRH_USERNAME not found"))
            password.set(ossrhPassword ?: error("OSSRH_PASSWORD not found"))
        }
    }
}