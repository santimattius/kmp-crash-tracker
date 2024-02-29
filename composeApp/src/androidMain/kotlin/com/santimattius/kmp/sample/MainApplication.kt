package com.santimattius.kmp.sample

import android.app.Application
import com.santimattius.crash.tracker.CrashTracker
import com.santimattius.crash.tracker.CrashTrackerConfig
import com.santimattius.crash.tracker.Providers

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrashTracker.initialize(
            config = CrashTrackerConfig(
                context = this,
                apiKey = BuildConfig.bugsnagApiKey
            ),
            providers = Providers.Default
        )
    }
}