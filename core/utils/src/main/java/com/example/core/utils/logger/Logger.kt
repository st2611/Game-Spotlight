package com.example.core.utils.logger

import android.util.Log

object Logger {

    fun d(message: String) = log(Log.DEBUG, message)
    fun i(message: String) = log(Log.INFO, message)
    fun w(message: String) = log(Log.WARN, message)
    fun e(message: String) = log(Log.ERROR, message)
    fun v(message: String) = log(Log.VERBOSE, message)
    fun wtf(message: String) = log(Log.ASSERT, message)

    private fun log(priority: Int, message: String) {
        val stackTrace = Throwable().stackTrace

        val target = stackTrace.firstOrNull {
            !it.className.contains("Logger") &&
                    !it.className.startsWith("java.lang") &&
                    !it.className.startsWith("kotlin.")
        }

        val tag = target?.className?.substringAfterLast('.') ?: "Unknown"
        val location = if (target != null) "(${target.fileName}:${target.lineNumber})" else ""

        Log.println(priority, tag, "[Logger] $location $message ")

    }
}