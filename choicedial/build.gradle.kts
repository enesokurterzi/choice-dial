plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
    id("signing")
}

group = "io.github.enesokurterzi"
version = "1.0.14"

android {
    namespace = "io.github.enesokurterzi"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = project.group as String
                artifactId = "choicedial"
                version = project.version as String

                pom {
                    name.set("ChoiceDial")
                    description.set("A lightweight customizable dial‑style picker library for Jetpack Compose.")
                    url.set("https://github.com/enesokurterzi/choice-dial")
                    licenses {
                        license {
                            name.set("Apache‑2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("enesokurterzi")
                            name.set("Enes Okurterzi")
                            email.set("enes.okurterzi98@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:github.com/enesokurterzi/choice-dial.git")
                        developerConnection.set("scm:git:ssh://github.com/enesokurterzi/choice-dial.git")
                        url.set("https://github.com/enesokurterzi/choice-dial")
                    }
                }
            }
        }
    }

    signing {
        useInMemoryPgpKeys(
            System.getenv("SIGNING_KEY"),
            System.getenv("SIGNING_PASSWORD")
        )
        sign(publishing.publications["release"])
    }
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
