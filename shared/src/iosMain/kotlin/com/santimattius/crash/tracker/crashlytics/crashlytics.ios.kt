package com.santimattius.crash.tracker.crashlytics

import co.touchlab.crashkios.crashlytics.enableCrashlytics
import co.touchlab.crashkios.crashlytics.setCrashlyticsUnhandledExceptionHook
import cocoapods.FirebaseCore.FIRApp
import com.santimattius.crash.tracker.CrashTrackerConfig
import com.santimattius.crash.tracker.CrashTrackerInitializer
import kotlinx.cinterop.ExperimentalForeignApi

actual fun provideCrashlyticsInitializer(): CrashTrackerInitializer {
    return IOSCrashlyticsCrashTrackerInitializer()
}

class IOSCrashlyticsCrashTrackerInitializer : CrashTrackerInitializer {
    @OptIn(ExperimentalForeignApi::class)
    override fun initialize(config: CrashTrackerConfig) {
        FIRApp.configure()
        enableCrashlytics()
        setCrashlyticsUnhandledExceptionHook()
    }
}