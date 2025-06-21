import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
    id("signing")
    id("com.vanniktech.maven.publish") version "0.30.0"
}

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

mavenPublishing {
    coordinates(
        groupId = "io.github.enesokurterzi",
        artifactId = "choicedial",
        version = "1.0.0"
    )

    pom {
        name.set("ChoiceDial")
        description.set("A lightweight customizable dial‑style picker library for Jetpack Compose.")
        inceptionYear.set("2025")
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
            url.set("https://github.com/enesokurterzi/choice-dial")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    /**
     * uncomment the following lines to enable signing of the artifacts and add this five properties to your gradle.properties file:
     * signing.keyId, signing.password, signing.key, mavenCentralUserName, mavenCentralPassword
     * after that run the following command to publish the library:
     * ./gradlew publishAndReleaseToMavenCentral
     */

//    signing {
//        useInMemoryPgpKeys(
//            project.findProperty("signing.key") as String,
//            project.findProperty("signing.password") as String
//        )
//        sign(publishing.publications)
//    }

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
