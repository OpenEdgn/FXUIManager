package com.github.open_edgn.fx.manager

import com.github.openEdgn.logger4k.getLogger
import com.github.open_edgn.fx.manager.application.fx.JFXApplication
import javafx.application.Application

/**
 *  启动器管理模块
 */
object UIApplication {
    private val logger = getLogger()

    @Volatile
    private lateinit var fxBoot: FXBoot

    /**
     * 程序启动方法
     *
     * @param args Array<String>
     */
    fun boot(boot: FXBoot) {
        fxBoot = boot
        InternalObjects.fxBoot = boot
        Application.launch(JFXApplication::class.java, *boot.args)
    }
}