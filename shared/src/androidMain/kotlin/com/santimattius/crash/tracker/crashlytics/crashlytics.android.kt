package com.santimattius.crash.tracker.crashlytics

import co.touchlab.crashkios.crashlytics.enableCrashlytics
import com.santimattius.crash.tracker.CrashTrackerConfig
import com.santimattius.crash.tracker.CrashTrackerInitializer

actual fun provideCrashlyticsInitializer(): CrashTrackerInitializer {
    return AndroidCrashlyticsCrashTrackerInitializer()
}


class AndroidCrashlyticsCrashTrackerInitializer : CrashTrackerInitializer {
    override fun initialize(config: CrashTrackerConfig) {
        enableCrashlytics()
    }
}