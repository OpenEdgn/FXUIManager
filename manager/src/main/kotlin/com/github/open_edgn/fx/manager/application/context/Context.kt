package com.github.open_edgn.fx.manager.application.context

import com.github.open_edgn.fx.manager.intent.Intent
import javafx.application.Platform

/**
 *  活动上下文
 */
abstract class Context {
    /**
     * 上下文名称
     */
    abstract val name: String

    /**
     * 在 JavaFX Application Thread上运行指定的Runnable。
     *
     */
    fun runUiOnThread(runnable: Runnable) {
        runUiOnThread {
            runnable.run()
        }
    }

    /**
     * 在 JavaFX Application Thread上运行指定的Runnable。
     *
     */
    fun runUiOnThread(runnable: () -> Unit) {
        Platform.runLater(runnable)
    }


    /**
     * 跳转到新的 Activity
     * @param intent Intent 事件意图
     */
    abstract fun startActivity(intent: Intent)

    /**
     * 启动一个服务
     *
     * @param intent Intent
     */
    abstract fun startService(intent: Intent)

    /**
     * 停止一个服务
     * @param intent Intent
     */
    abstract fun stopService(intent: Intent)

}