package com.santimattius.crash.tracker.bugsnag

import co.touchlab.crashkios.bugsnag.enableBugsnag
import com.bugsnag.android.Bugsnag
import com.santimattius.crash.tracker.CrashTrackerConfig
import com.santimattius.crash.tracker.CrashTrackerInitializer

actual fun provideBugsnagInitializer(): CrashTrackerInitializer {
    return AndroidBugsnagInitializer()
}

class AndroidBugsnagInitializer : CrashTrackerInitializer {
    override fun initialize(config: CrashTrackerConfig) {
        Bugsnag.start(config.context, config.apiKey)
        enableBugsnag()
    }
}