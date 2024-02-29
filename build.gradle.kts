plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.cocoaPods) apply false
    alias(libs.plugins.crashkiosCrashlytics) apply false
    alias(libs.plugins.crashkiosBugsnag) apply false
    alias(libs.plugins.bugsnagAndroid) apply false
    alias(libs.plugins.googlePlayService) apply false
    alias(libs.plugins.crashlyticsPlugin) apply false
    alias(libs.plugins.google.secrets.gradle.plugin) apply false
}