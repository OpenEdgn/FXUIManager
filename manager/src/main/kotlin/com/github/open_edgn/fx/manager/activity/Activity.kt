package com.github.open_edgn.fx.manager.activity

import com.github.open_edgn.fx.manager.InternalObjects
import com.github.open_edgn.fx.manager.activity.event.IActivityEvent
import com.github.open_edgn.fx.manager.application.context.InternalContext
import com.github.open_edgn.fx.manager.intent.Intent
import com.github.open_edgn.fx.manager.window.Window
import javafx.scene.Node
import java.net.URL


/**
 * 活动组件
 */
abstract class Activity<T : Node> : InternalContext(), IActivityEvent, IParent<T> {

    override val icon: URL = javaClass.getResource("/icon/icon.png")

    override val styles: Array<URL> = arrayOf()

    override val name: String
        get() = title

    lateinit var intent: Intent

    override lateinit var window: Window

    override fun onCreate() {

    }

    override fun onStart() {

    }

    override fun onHide() {

    }

    override fun onDestroy() {

    }

    fun finish() {
        InternalObjects.container.destroy(this::class)
    }

}