package com.github.open_edgn.fx.manager.application

import com.github.open_edgn.fx.manager.FXBoot
import com.github.open_edgn.fx.manager.activity.Activity
import com.github.open_edgn.fx.manager.application.context.Context
import com.github.open_edgn.fx.manager.intent.Intent
import javafx.scene.Node
import javafx.stage.Stage
import kotlin.reflect.KClass


/**
 * 容器生命周期
 */
interface IContainer {

    val bootContext: Context

    /**
     * 容器初始化方法，在程序启动时会调用
     *
     * 此方法仅被调用一次
     *
     * @param stage Stage 父容器构造方法
     */
    fun containerInit(stage: Stage)


    /**
     * 显示一个 Activity
     *
     * @param intent Intent
     */
    fun showActivity(intent: Intent)

    /**
     * 容器销毁方法，仅在程序退出时被调用
     */
    fun containerDestroy()

    /**
     * 销毁活动
     * @param activityClass KClass<Activity<Node>>
     */
    fun destroy(activityClass: KClass<out Activity<*>>)

}