package com.santimattius.crash.tracker

import com.santimattius.crash.tracker.bugsnag.BugsnagCrashTrackerProvider
import com.santimattius.crash.tracker.crashlytics.CrashlyticsCrashTrackerProvider
import kotlin.native.concurrent.ThreadLocal

class CrashTracker private constructor(
    private val providers: List<CrashTrackerProvider>
) {

    fun logMessage(message: String) {
        providers.forEach { it.logMessage(message) }
    }

    fun sendHandledException(throwable: Throwable) {
        providers.forEach { it.sendHandledException(throwable) }
    }

    fun sendFatalException(throwable: Throwable) {
        providers.forEach { it.sendFatalException(throwable) }
    }

    fun setCustomValue(section: String, value: Map<String, String>) {
        providers.forEach { it.setCustomValue(section, value) }
    }

    @ThreadLocal
    companion object {

        private var instance: CrashTracker? = null

        fun initialize(config: CrashTrackerConfig, providers: Providers = Providers.Default) {
            val collection = when (providers) {
                Providers.Crashlytics -> listOf(CrashlyticsCrashTrackerProvider())
                Providers.Bugsnag -> listOf(BugsnagCrashTrackerProvider())
                Providers.Default -> listOf(
                    CrashlyticsCrashTrackerProvider(),
                    BugsnagCrashTrackerProvider()
                )
            }
            collection.forEach { it.configure(config) }
            instance = CrashTracker(collection)

        }

        fun instance(): CrashTracker {
            return instance
                ?: throw IllegalStateException("CrashTracker not initialized, please run initialize")
        }
    }
}