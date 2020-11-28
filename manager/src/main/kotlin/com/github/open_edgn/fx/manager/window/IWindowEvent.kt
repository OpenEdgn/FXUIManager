package com.github.open_edgn.fx.manager.window

interface IWindowEvent {


    /**
     * 全屏事件
     * @param window Window
     */
    fun onFullScreen(window: Window)

    /**
     *
     * 窗口移动事件
     *
     * @param window Window
     */
    fun onMove(window: Window)


    /**
     * 窗口大小变更事件
     * @param window Window
     */
    fun onReSize(window: Window)

}