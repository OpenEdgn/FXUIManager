package com.github.open_edgn.fx.manager.utils

import com.github.open_edgn.fx.manager.application.IContainer
import com.github.open_edgn.fx.manager.window.IWindowEvent
import com.github.open_edgn.fx.manager.window.Window
import javafx.beans.value.ObservableValue
import javafx.event.EventHandler
import javafx.stage.Stage


object StageUtils {
    /**
     * 绑定窗体事件
     *
     * @param container IContainer
     * @param stage Stage
     */
    fun registerEvent(container: IContainer, stage: Stage) {
        // 注册销毁事件
        stage.onCloseRequest = EventHandler() {
            container.containerDestroy()
        }
    }

    /**
     *  注册窗口变动事件
     *
     */
    fun registerEvent(windowEvent: IWindowEvent, stage: Stage, window: Window) {
        stage.fullScreenProperty().addListener { _, _, _ ->
            windowEvent.onFullScreen(window)
        }
        val moveFunc: (observable: ObservableValue<out Number>, oldValue: Number, newValue: Number) -> Unit =
                { _, _, _ ->
                    windowEvent.onMove(window)
                }
        stage.xProperty().addListener(moveFunc)
        stage.yProperty().addListener(moveFunc)
        val resizeFunc: (observable: ObservableValue<out Number>, oldValue: Number, newValue: Number) -> Unit =
                { _, _, _ ->
                    windowEvent.onReSize(window)
                }
        stage.widthProperty().addListener(resizeFunc)
        stage.heightProperty().addListener(resizeFunc)
    }
}