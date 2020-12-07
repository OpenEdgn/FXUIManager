package com.github.open_edgn.fx.manager

import com.github.open_edgn.fx.manager.activity.Activity
import java.net.URL
import kotlin.reflect.KClass

/**
 *  启动
 */
class FXBoot private constructor(val activity: KClass<out Activity<*>>) {
    private lateinit var internalIcon: URL
    val icon: URL
        get() = internalIcon
    val args: Array<String>
        get() = iArgs
    private lateinit var iArgs: Array<String>
    val styles: List<URL> = arrayListOf()

    class Builder(private val activity: KClass<out Activity<*>>) {
        var args: Array<String> = arrayOf()
        private val styles = arrayListOf<URL>()
        private var internalIcon: URL = javaClass.getResource("/icon/icon.png")

        fun setIcon(url: URL):Builder{
            internalIcon = url
            return this
        }

        fun addStyleUrl(url: URL): Builder {
            styles.add(url)
            return this
        }

        fun build(): FXBoot {
            val fxBoot = FXBoot(activity)
            fxBoot.iArgs = args
            (fxBoot.styles as MutableList).addAll(styles)
            fxBoot.internalIcon = internalIcon
            return fxBoot
        }
    }
}