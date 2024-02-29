package com.santimattius.crash.tracker.bugsnag

import co.touchlab.crashkios.bugsnag.BugsnagKotlin
import com.santimattius.crash.tracker.CrashTrackerConfig
import com.santimattius.crash.tracker.CrashTrackerInitializer
import com.santimattius.crash.tracker.CrashTrackerProvider
import log

class BugsnagCrashTrackerProvider(
    private val initializer: CrashTrackerInitializer = provideBugsnagInitializer(),
) : CrashTrackerProvider {

    override fun configure(config: CrashTrackerConfig) {
        initializer.initialize(config)
    }

    override fun logMessage(message: String) {
        log(this::class.simpleName + "logMessage")
        BugsnagKotlin.logMessage(message)
    }

    override fun sendHandledException(throwable: Throwable) {
        log(this::class.simpleName + "sendHandledException")
        BugsnagKotlin.sendHandledException(throwable)
    }

    override fun sendFatalException(throwable: Throwable) {
        log(this::class.simpleName + "sendHandledException")
        BugsnagKotlin.sendFatalException(throwable)
    }

    override fun setCustomValue(section: String, value: Map<String, String>) {
        log(this::class.simpleName + "setCustomValue")
        value.forEach {
            BugsnagKotlin.setCustomValue(section, it.key, it.value)
        }
    }
}