import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.cocoaPods)
    alias(libs.plugins.crashkiosCrashlytics)
    alias(libs.plugins.crashkiosBugsnag)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        name = "Shared"
        ios.deploymentTarget = "14.0"
        framework {
            baseName = "Shared"
            isStatic = false
            export(libs.crashkios.bugsnag)
            transitiveExport = true // This is default.
        }

        pod("Bugsnag")
        pod("FirebaseCore")
        pod("FirebaseCrashlytics")
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.crashkios.bugsnag)
            implementation(libs.crashkios.crashlytics)
        }
        androidMain.dependencies {
            implementation(libs.bugsnag.android)
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.crashlytics)
        }
    }
}

android {
    namespace = "com.santimattius.kmp.skeleton.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
