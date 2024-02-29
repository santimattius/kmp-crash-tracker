package com.santimattius.crash.tracker

interface CrashTrackerProvider {

    fun configure(config: CrashTrackerConfig)

    fun logMessage(message: String)

    fun sendHandledException(throwable: Throwable)

    fun sendFatalException(throwable: Throwable)

    fun setCustomValue(section: String, value: Map<String, String>)
}