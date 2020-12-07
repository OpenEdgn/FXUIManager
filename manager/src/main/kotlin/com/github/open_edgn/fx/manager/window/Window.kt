package com.github.open_edgn.fx.manager.window

import com.github.open_edgn.fx.manager.application.context.Context
import com.github.open_edgn.fx.manager.application.context.InternalContext
import javafx.stage.Stage
import javafx.stage.Window


/**
 * 窗体控制
 */
abstract class Window : InternalContext() {

    /**
     * 得到JavaFX Window 对象
     */
    abstract val fxWindow: Window

    /**
     * 得到 JavaFX Stage 对象
     */
    abstract val fxStage: Stage

    /**
     * 是否置于顶部
     */
    abstract var isAlwaysOnTop: Boolean

    /**
     * 是否全屏与控制全屏
     */
    abstract var isFullScreen: Boolean

    /**
     * 窗体是否显示
     */
    abstract var show: Boolean

    /**
     * 窗口的宽度
     */
    abstract var width: Double

    /**
     * 窗口的高度
     */
    abstract var height: Double

    /**
     * 窗口的 X 轴位置
     */
    abstract var x: Double

    /**
     * 窗口的 Y 轴位置
     */
    abstract var y: Double


    /**
     * 窗口标题
     */
    abstract var title: String

    /**
     * 窗口是否为最大
     */
    abstract var isMaximized: Boolean

    /**
     * 窗口大小是否可变
     */
    abstract var isResizable: Boolean
}