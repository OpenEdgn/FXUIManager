package com.github.open_edgn.fx.manager

import com.github.open_edgn.fx.manager.activity.Activity
import kotlin.reflect.KClass

/**
 *  启动
 */
class FXBoot private constructor(val activity: KClass<out Activity<*>>) {
    val args: Array<String>
        get() = iArgs
    private lateinit var iArgs: Array<String>

    class Builder(private val activity: KClass<out Activity<*>>) {
        var args: Array<String> = arrayOf()

        fun build(): FXBoot {
            val fxBoot = FXBoot(activity)
            fxBoot.iArgs = args
            return fxBoot
        }
    }
}