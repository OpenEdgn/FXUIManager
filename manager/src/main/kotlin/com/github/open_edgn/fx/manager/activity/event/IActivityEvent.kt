package com.github.open_edgn.fx.manager.activity.event

import com.github.open_edgn.fx.manager.window.Window

/**
 * 活动事件绑定
 */
interface IActivityEvent {

    /**
     * 得到窗体对象
     */
    val window: Window

    /**
     *  活动创建
     */
    fun onCreate()

    /**
     * 活动启动
     */
    fun onStart()

    /**
     * 活动隐藏
     */
    fun onHide()

    /**
     * 活动销毁
     */
    fun onDestroy()
}