package com.github.open_edgn.fx.manager.application

import com.github.openEdgn.logger4k.getLogger
import com.github.open_edgn.fx.manager.InternalObjects
import com.github.open_edgn.fx.manager.activity.Activity
import com.github.open_edgn.fx.manager.application.context.Context
import com.github.open_edgn.fx.manager.application.context.InternalContext
import com.github.open_edgn.fx.manager.intent.Intent
import com.github.open_edgn.fx.manager.utils.ActivityUtils
import com.github.open_edgn.fx.manager.utils.StageUtils
import com.github.open_edgn.fx.manager.window.IWindowEvent
import com.github.open_edgn.fx.manager.window.InternalWindow
import com.github.open_edgn.fx.manager.window.Window
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import java.lang.RuntimeException
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.system.exitProcess

// 默认容器
object InternalContainer : IContainer, IWindowEvent {
    private val logger = getLogger()
    override val bootContext: Context = BootContext()

    @Volatile
    private var started: Boolean = false

    private lateinit var window: Window

    private val root = VBox()

    private val scene by lazy {
        root.id = "body"
        VBox.setVgrow(root, Priority.ALWAYS)
        root.alignment = Pos.CENTER
        val sce = Scene(root)
        sce.fill = Color.TRANSPARENT
        root.background = Background(BackgroundFill(Color.TRANSPARENT,null,null))
        sce
    }
    private lateinit var stage: Stage

    @Synchronized
    override fun containerInit(stage: Stage) {
        if (started) {
            throw RuntimeException("此方法已被加载！")
        }
        logger.trace("初始化 stage.")
        this.stage = stage
        started = true
        window = InternalWindow(stage)
        StageUtils.registerEvent(this as IWindowEvent, stage, window)
        StageUtils.registerEvent(this as IContainer, stage)
        logger.trace("绑定 stage 事件.")
        stage.scene = scene
        showActivity(Intent(bootContext, InternalObjects.fxBoot.activity))
        stage.show()
        logger.trace("UI 已展示.")
    }

    private val headActivity: Activity<out Node>?
        get() = synchronized(activityList) {
            if (activityList.isEmpty()) {
                null
            } else {
                activityList.first
            }
        }

    @Volatile
    private var bindWindowEvent = false

    private val activities = ConcurrentHashMap<KClass<Activity<Node>>, Activity<*>>()

    @Volatile
    private var stopEvent = false

    private val activityList = LinkedList<Activity<out Node>>()

    /**
     * 将UI 展示
     * @param intent Intent
     */
    @Suppress("UNCHECKED_CAST")
    @Synchronized
    override fun showActivity(intent: Intent) {
        stopEvent = true
        val key = intent.out as KClass<Activity<Node>>
        var create: () -> Unit = {
            logger.trace("活动 {} 已存在.", key.simpleName)
        }
        val get = activities[key]
        val activity = if (get == null) {
            val createActivity = ActivityUtils.createActivity(key)
            activities[key] = createActivity
            logger.trace("活动{}不存在，将创建新活动.", key.simpleName)
            create = { createActivity.onCreate() }
            createActivity
        } else {
            get
        }

        flushActivity(activity) {
            it.intent = intent
            create()
        }
        stopEvent = false
    }

    private fun flushActivity(activity: Activity<out Node>, event: (Activity<out Node>) -> Unit = {}) {
        headActivity?.run {
            if (headActivity != activity) {
                logger.trace("活动 {} 已隐藏 .", javaClass.simpleName)
                runUiOnThread {
                    onHide()
                }
            }
        }
        val parent = activity.root as Parent
        VBox.setVgrow(parent, Priority.ALWAYS)
        parent.maxWidth(Double.MAX_VALUE)
        parent.maxHeight(Double.MAX_VALUE)
        scene.stylesheets.clear()
        scene.root = parent
        stage.icons.removeAll()
        logger.trace("展示活动 {}.", activity.javaClass.simpleName)
        stage.icons.add(Image(activity.icon.openStream()))
        scene.stylesheets.addAll(InternalObjects.fxBoot.styles.map { it.toExternalForm() })
        scene.stylesheets.addAll(activity.styles.map { it.toExternalForm() })
        activity.window = window
        bindWindowEvent = activity is IWindowEvent

        activity.runUiOnThread {
            event(activity)
            if (!stage.isMaximized) {
                stage.width = parent.prefWidth(0.0)
                stage.height = parent.prefHeight(0.0)
            }
            stage.minHeight = parent.minHeight(0.0)
            stage.minWidth = parent.minWidth(0.0)
            activity.onStart()
        }
        window.title = activity.title
        synchronized(activityList) {
            activityList.remove(activity)
            activityList.addFirst(activity)
        }
    }

    @Synchronized
    override fun destroy(activityClass: KClass<out Activity<*>>) {
        if (activityList.size >= 2) {
            flushActivity(activityList[1]) {}
        }
        val remove = activities.remove(activityClass) ?: return
        synchronized(activityList) {
            activityList.remove(remove)
        }
        logger.trace("开始销毁活动 {}.", activityClass.simpleName)
        remove.runUiOnThread { remove.onDestroy()
            logger.trace("销毁活动 {} 完成.", activityClass.simpleName)
        }
        if (activityList.isEmpty()) {
            logger.trace("活动 {} 已为最后活动，程序退出.", activityClass.simpleName)
            stage.close()
        }
    }

    override fun containerDestroy() {
        logger.trace("触发窗口销毁.")
        synchronized(activityList) {
            headActivity?.run {
                runUiOnThread {
                    onHide()
                }
            }
            for (activity in activityList) {
                activity.runUiOnThread {
                    activity.onDestroy()
                    logger.trace("栈底活动 {} 已销毁.",activity::class.simpleName)

                }
            }
        }
        exitProcess(0)
    }

    override fun onFullScreen(window: Window) {
        logger.trace("全屏状态更新 => {}", window.isFullScreen)
        if (bindWindowEvent && !stopEvent) {
            (headActivity as IWindowEvent).onFullScreen(window)
        }
    }

    override fun onMove(window: Window) {
        if (bindWindowEvent && !stopEvent) {
            (headActivity as IWindowEvent).onMove(window)
        }
    }

    override fun onReSize(window: Window) {
        if (bindWindowEvent && !stopEvent) {
            (headActivity as IWindowEvent).onReSize(window)
        }
    }

    internal class BootContext : InternalContext() {
        override val name: String = "Boot"
    }

}