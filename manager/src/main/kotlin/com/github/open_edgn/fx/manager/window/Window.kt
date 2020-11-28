package com.github.open_edgn.fx.manager.window

import com.github.open_edgn.fx.manager.application.context.Context
import com.github.open_edgn.fx.manager.application.context.InternalContext


/**
 * 窗体控制
 */
abstract class Window : InternalContext() {

    /**
     * 是否置于顶部
     */
    abstract var alwaysOnTop: Boolean

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
    abstract var title:String
}