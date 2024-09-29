import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
}

android {
    namespace = "com.jumrukovski.quotescompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jumrukovski.quotescompose"
        minSdk = 26
        targetSdk = 35
        versionCode = 3
        versionName = "1.2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    ktlint {
        this.android.set(true)
        this.ignoreFailures.set(false)
        this.reporters {
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
        }
    }

    tasks.preBuild {
        dependsOn("ktlintFormat", "detekt")
    }
    tasks.detekt.dependsOn("ktlintFormat")

    detekt {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom("$projectDir/config/detekt.yml")
        baseline = file("$projectDir/config/baseline.xml")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeCompiler {
        enableStrongSkippingMode = true
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
    }
}

dependencies {
    // core
    implementation("androidx.core:core-ktx:1.13.1")

    // tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")

    // compose
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.compose.material3:material3:1.3.0")
    platform("androidx.compose:compose-bom:2024.09.02")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")
    testImplementation("com.google.truth:truth:1.4.2")

    // compose extensions
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    // lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")

    // navigation component
    implementation("androidx.navigation:navigation-compose:2.8.1")

    // dagger Hilt dependency injection
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-compiler:2.51.1")

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.9.2")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    // Retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Okhttp3
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
}
