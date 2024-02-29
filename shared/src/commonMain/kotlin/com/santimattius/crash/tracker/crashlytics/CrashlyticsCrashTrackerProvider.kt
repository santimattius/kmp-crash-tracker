package com.santimattius.crash.tracker.crashlytics

import co.touchlab.crashkios.crashlytics.CrashlyticsKotlin
import com.santimattius.crash.tracker.CrashTrackerConfig
import com.santimattius.crash.tracker.CrashTrackerInitializer
import com.santimattius.crash.tracker.CrashTrackerProvider

class CrashlyticsCrashTrackerProvider(
    private val initializer: CrashTrackerInitializer = provideCrashlyticsInitializer()
) : CrashTrackerProvider {
    override fun configure(config: CrashTrackerConfig) {
        initializer.initialize(config)
    }

    override fun logMessage(message: String) {
        CrashlyticsKotlin.logMessage(message)
    }

    override fun sendHandledException(throwable: Throwable) {
        CrashlyticsKotlin.sendHandledException(throwable)
    }

    override fun sendFatalException(throwable: Throwable) {
        CrashlyticsKotlin.sendFatalException(throwable)
    }

    override fun setCustomValue(section: String, value: Map<String, String>) {
        val customValues = value.toMutableMap()
        customValues["section"] = section
        customValues.forEach {
            CrashlyticsKotlin.setCustomValue(it.key, it.value)
        }
    }
}