package com.santimattius.crash.tracker.bugsnag

import co.touchlab.crashkios.bugsnag.enableBugsnag
import cocoapods.Bugsnag.Bugsnag
import com.santimattius.crash.tracker.CrashTrackerConfig
import com.santimattius.crash.tracker.CrashTrackerInitializer
import kotlinx.cinterop.ExperimentalForeignApi

actual fun provideBugsnagInitializer(): CrashTrackerInitializer {
    return IOSBugsnagInitializer()
}

class IOSBugsnagInitializer : CrashTrackerInitializer {
    @OptIn(ExperimentalForeignApi::class)
    override fun initialize(config: CrashTrackerConfig) {
        Bugsnag.startWithApiKey(config.apiKey)
        enableBugsnag()
    }
}