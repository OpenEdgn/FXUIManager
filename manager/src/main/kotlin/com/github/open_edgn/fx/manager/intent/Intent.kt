package com.github.open_edgn.fx.manager.intent

import com.github.open_edgn.fx.manager.application.context.Context
import java.io.Closeable
import java.io.Serializable
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

final class Intent(val context: Context, val out: KClass<out Context>) : Closeable {
    private val extras = ConcurrentHashMap<String, Serializable>(0)
    fun <T : Serializable> putExtra(key: String, any: T):Intent {
        extras[key] = any
        return this
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Serializable> getExtra(key: String, defaultValue: T): T {
        return (extras[key] ?: defaultValue) as T
    }

    override fun close() {
        extras.clear()
    }
}